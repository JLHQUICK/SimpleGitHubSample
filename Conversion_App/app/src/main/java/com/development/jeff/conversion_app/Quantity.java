package com.development.jeff.conversion_app;

/**
 * Created by Jeff on 8/13/2015.
 * Quantity is used for doing all the conversion from one unit to another.
 */

import java.text.DecimalFormat;

public class Quantity {

    // Each object will have both a value and a unit of measure
    final double value;//                               EX. 10
    final Unit unit;//                                  EX. tbs

    // Enum types use a CONSTANT key to represent a value. They don't have to be explicitly instantiated in gradle.
    // They allow us to easily define how to convert all the other types
    // of measurements to convert from teaspoon to anything. Then to make
    // any conversion we convert from the starting type to teaspoon and then
    // to the final required type.

    public static enum Unit {
        tsp(1.0d), tbs(0.3333d), cup(0.0208d), oz(0.1666d),
        pint(0.0104d), quart(0.0052d), gallon(0.0013d), pound(0.0125d),
        ml(4.9289d), liter(0.0049d), mg(5687.5d), kg(0.0057d);

        //This method of adding extra processing within the enum seems like an extension to Java. Perhaps it is the gradle plugin allowing it...but it seems like a Hack.
        //Used as a way to add functionality to the enum itself instead of doing explicit validation.
        // We define that tsp will be the base unit of measure that we will
        // convert to and then convert from. When referencing the enum elsewhere, this will be presented as one of the values to Quantity.Unit.%
        final static Unit baseUnit = tsp;

        // Will hold the number of tsp the original unit converts to
        final double byBaseUnit;

        //Below is an enum constructor...basically the enum is like an inner class.
        // Receives the number of tsps the starting unit equals.
        // I think that I see where this is getting set. Whenever Quantity.Unit.% is called, it runs the below piece of code like a constructor for the enum type called Unit.
        // When this happens, it is as if whatever is in place of the % (tbs for example) will determine what byBaseUnit will be. Ex when tbs it will be 0.3333, when gallons then 0.0013.
        private Unit(double inTsp) {
            this.byBaseUnit = inTsp;
        }

        // Converts any other unit value to the number of tsps
        public double toBaseUnit(double value) {
            return value / byBaseUnit;
        }

        // We convert to another unit by using the teaspoon conversion percent
        // defined in the enum
        public double fromBaseUnit(double value) {
            return value * byBaseUnit;
        }

    }

    // The constructor that receives the value and unit of measure
    public Quantity(double value, Unit unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    //Requires that the original type first be converted to tsp (by passing Quantity.Unit.tsp as a parameter)...Then can convert that to the desired unit passed as a parameter
    public Quantity convertTo(Unit newUnit) {
        //Ex. passing in tbs. newUnit is tbs. oldUnit will become, or point to, tbs.
        Unit oldUnit = this.unit;
        //A new Quantity is created. It's value will essentially be the number associated with the type chosen in the spinner multiplied by the number associated with unit type just passed in.
        return new Quantity(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)), newUnit);
    }

    // Prints out to screen the unit amount and unit type. Is called by the MainActivity using the .toString() inline with the call to convertTo.
    // Pass back the value stored in the Quantity (which was just created in convertTo)
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.0000");

        return df.format(value) + " " + unit.name();
    }

}