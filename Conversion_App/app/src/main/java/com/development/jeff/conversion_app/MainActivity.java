package com.development.jeff.conversion_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private Spinner convertToUnitTypeSpinner;

    private EditText amountToConvertTextView;

    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, literTextView, milligramTextView, kilogramTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Set the interface formatting .xml file

        // Fills the spinner with the unit options
        addItemsToConversionUnitSpinner();

        // Add listener to the Spinner
        addListenerToUnitTypeSpinner();

        // Get a reference to the edit text view to retrieve the amount of the unit type
        amountToConvertTextView = (EditText) findViewById(R.id.amount_text_view);

        initializeTextViews(); //Just get the TextView objects above connected as references to the TextViews in the GridLayout.

        //At this point the app is running and the app is waiting for activity that will fire convertToUnitTypeSpinner.onItemSelected()

    }

    public void initializeTextViews(){
    //Just get the TextView objects above connected as references to the TextViews in the GridLayout.
        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);

    }

    public void addItemsToConversionUnitSpinner(){

        // Get a reference to the spinner. The items in the .xml format files are instantiated as objects by Android itself. Sometimes we get a reference by using the new keyword.
        convertToUnitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);//Also, we are casting the View (findViewById returns a View type) to a Spinner type.

        // Create an ArrayAdapter using the string array and a default spinner layout. For some reason, the ArrayAdapter uses the < > to identify the internal type of data.
        //Here we are using a .xml file with an array in it to populate the adapter with spinner items. The conversion_types.xml is in the values folder and the array has a "name" of "conversion_types".
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);//We want "simple_spinner_item"s because they will be populated into a Spinner object(s).

        // Specify the layout to use when the list of choices appears. Odd that I need to set the formatting of the list here. Why not in the .xml file?
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner. Finally we get the Spinner to have access to the array items.
        convertToUnitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner() {
        convertToUnitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);//Again we create an internal reference to the existing Spinner.
        //Connect the Spinner to code (defined here) that will occur when a spinner item is selected.
        convertToUnitTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3)
            {
                // Get the item selected in the Spinner. Internal string for passing around, so toString had to be called.
                //Notice that it's treating the Item passed in as an AdapterView instead of a Spinner...Also treated like an array with the pos number.
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();

                // Verify if I'm converting from teaspoon so that I use the right
                // conversion algorithm
                checkIfConvertingFromTsp(itemSelectedInSpinner);

            }
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO maybe add something here later
            }
        });
    }

    public void checkIfConvertingFromTsp(String currentUnitString){

        if(currentUnitString.equals("teaspoon")){

            updateUnitTypesUsingTsp(Quantity.Unit.tsp); //Notice that Quantity (as in our custom Class Quantity) is being referred to here...particularly the key "tsp" in the "Unit" enum.
            // How? It seems that gradle is handling this reference. Additionally, I suppose that, since the enum named "Unit" is static, that Java will have already created a copy of
            // it in memory...and that when this occurs Java limits the values to the enum.

        } else {

            if(currentUnitString.equals("tablespoon")){

                updateUnitTypesUsingOther(Quantity.Unit.tbs);

            } else if(currentUnitString.equals("cup")){

                updateUnitTypesUsingOther(Quantity.Unit.cup);

            } else if(currentUnitString.equals("ounce")){

                updateUnitTypesUsingOther(Quantity.Unit.oz);

            } else if(currentUnitString.equals("pint")){

                updateUnitTypesUsingOther(Quantity.Unit.pint);

            } else if(currentUnitString.equals("quart")){

                updateUnitTypesUsingOther(Quantity.Unit.quart);

            } else if(currentUnitString.equals("gallon")){

                updateUnitTypesUsingOther(Quantity.Unit.gallon);

            } else if(currentUnitString.equals("pound")){

                updateUnitTypesUsingOther(Quantity.Unit.pound);

            } else if(currentUnitString.equals("milliliter")){

                updateUnitTypesUsingOther(Quantity.Unit.ml);

            } else if(currentUnitString.equals("liter")){

                updateUnitTypesUsingOther(Quantity.Unit.liter);

            } else if(currentUnitString.equals("milligram")){

                updateUnitTypesUsingOther(Quantity.Unit.mg);

            } else {

                updateUnitTypesUsingOther(Quantity.Unit.kg);

            }

        }

    }

    public void updateUnitTypesUsingTsp(Quantity.Unit currentUnit){

        // Convert the value in the EditText box to a double
        double doubleToConvert = Double.parseDouble(amountToConvertTextView.getText().toString());

        // Combine value to unit
        String teaspoonValueAndUnit = doubleToConvert + " tsp";

        // Change the value for the teaspoon TextView
        teaspoonTextView.setText(teaspoonValueAndUnit);

        // Update all the Unit Text Fields. Looks like we aren't even using currentUnit from the passed in value.
        //Anyway, the Quantity.Unit.% is actually passing some custom Unit type that will match the key in the enum.
        //Also it is passing the handle to the TextView(s) in the results GridView.
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogramTextView);

    }

    //Note: this method is overridden below. In other words, it has 2 different versions.
    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo,
                                            TextView theTextView){

        //And FINALLY! we are creating a reference to a Quantity object. Probably because we want to use the functions therein.
        //Passing in the values for Quantity.value (a double from the editText) and the Quantity.Unit.% type (EX.'tsp'). 'tsp' is a Unit enum key thing that will be internally related to a value in the enum
        //Quantity value of number typed in and a Unit of type tsp is created.
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);
        //Just keep in mind that at this point there IS a Quantity with the value of the double, and a Unit.byBaseUnit = 1.0 because it was referred to with tsp.

        //Here the convertTo function (original function name was 'to' which is a stupid name for a function) is called passing in the unit type/enum key and converting that to a string.
        // Here we are using gradle mechanism for stringing together function calls to:
        //  First convert unitQuantity to the equivalent tsp Quantity/Unit. In the case of 10 and gallons it would return a tsp type Quantity with value = 7692.30769231
        //  Then the returned quantity of type tsp and value 7692.30769231
        //   Finally we call the toString on the Quantity that was last passed back by the convertTo function.
        String tempUnit = unitQuantity.convertTo(unitConvertingTo).toString();//Here we use the gradle mechanism to run convertTo on the Quantity.Unit and then toString on the passed back Quantity object in that order.

        theTextView.setText(tempUnit);
    }

    public void updateUnitTypesUsingOther(Quantity.Unit currentUnit){

        // Convert the value in the EditText box to a double
        double doubleToConvert = Double.parseDouble(amountToConvertTextView.getText().toString());

        // Create a Quantity using the currentUnit. Explicitly create a Quantity Let's say value 10 unit gallons (implicitly a If it were gallons it would set the byBaseUnit = 0.0013)
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        // Create the String for the teaspoon TextView
        String valueInTeaspoons = currentQuantitySelected.convertTo(Quantity.Unit.tsp).toString();//So if 10 and gallons were passed in, then the math would be the amount of gallons (10) divided
                                                                                                    // by the gallon/tsp rate (0.0013) = ~7692.30769231
                                                                                                    // ...also multiplied by 1 (the base conversion of tsp/tsp) but that won't matter much.

        // Set the text for the teaspoon TextView. This will be the amount in teaspoons that was converted from the amount put in.
        teaspoonTextView.setText(valueInTeaspoons);

        //And now we get all the other conversions in a similar way But instead the called function will end up multiplying by something other than the tsp/tsp amount.
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.tbs, tablespoonTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.cup, cupTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.oz, ounceTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.pint, pintTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.quart, quartTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.gallon, gallonTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.pound, poundTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.ml, milliliterTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.liter, literTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.mg, milligramTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.kg, kilogramTextView);


        // Set the currently selected unit to the number in the EditText
        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){

            // Create the TextView text by taking the value in EditText and adding
            // on the currently selected unit in the spinner
            String currentUnitTextViewText = doubleToConvert + " " +
                    currentQuantitySelected.unit.name();

            // Create the TextView name to change by getting the currently
            // selected quantities unit name and tacking on _text_view
            String currentTextViewName = currentQuantitySelected.unit.name() +
                    "_text_view";

            // Get the resource id needed for the textView to use in findViewById
            int currentId = getResources().getIdentifier(currentTextViewName, "id",
                    MainActivity.this.getPackageName());

            // Create an instance of the TextView we want to change
            TextView currentTextView = (TextView) findViewById(currentId);

            // Put the right data in the TextView
            currentTextView.setText(currentUnitTextViewText);

        }

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit, TextView targetTextView){

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);//Let's say passing 10 and gallons

        // Algorithm used quantityInTbs.convertTo(Unit.tsp).convertTo(Unit.ounce)
        // Here we are using gradle mechanism for stringing together function calls to:
        //  First convert currenntQuantity to the equivalent tsp Quantity/Unit. In the case of 10 and gallons it would return a tsp type Quantity with value = 7692.30769231
        //  Then the returned quantity of type tsp and value 7692.30769231 ...and now would be sent to convert against, let's say tbs
        //      So, we send in a tsp with value 7692.30769231 ...and a preferred unit of tbs
        //          and get back a tbs with value of 2563.84615385
        //              Finally we call the toString on the Quantity that was last passed back by the convertTo function.
        String tempTextViewText = currentQuantitySelected.convertTo(Quantity.Unit.tsp).
                convertTo(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }

}