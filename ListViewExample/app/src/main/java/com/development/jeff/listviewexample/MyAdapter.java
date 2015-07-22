package com.development.jeff.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class MyAdapter extends ArrayAdapter<String> {
    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout_2, values);
    }
    //Evidently getView is called by the ArrayAdapter constructor and loops through the array and ViewGroup indexes to populate the entire ViewGroup.
    //Since this is a base getView method for ArrayAdapter and we want to customize that so we use @Override
    // Because we want to use the same number and types of arguments, overloading will not work.
    // I also think that the Override is there so that the code for the super will not get brought over into the Byte/Dalvik code...in order to be more resource efficient.
    // Basically this will create rows that are made up of View items and populate the data into those View items.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //This LayoutInflater classe will be able to put our layout(essentially the main place where our display elements are defined/formatted) into the right View
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        //And here we actually use the LayoutInflater to put the layout (format) into this local view that we just created.
        //Note that we aren't going to pass in a "parent" in this case (parent is the internal ViewGroup that will be populated by ArrayAdapter when we return this View to it.)
        // so we put that "false" after it here because the LayoutInflater constructor needs that argument.
        View theView = theInflater.inflate(R.layout.row_layout_2, parent, false);

        //Put one of the values from the data source into a String. This is acting as a local pointer to the array index that has been passed in.
        //At first I named this myFamily, but I realize that this type of class really should be written more generically. I should be able to use this for any array of string values.
        //getItem is internal to ArrayAdapter to point to the passed in data elements.
        String myString = getItem(position);

        //Create a local way to point to the TextView defined in our layout/xml file.
        // Maybe what's really happening here is that the layout file is just a template...and that the class MUST actually create an instance of it.
        //...otherwise it seems silly to do this.
        TextView theTextView = (TextView) theView.findViewById(R.id.textView1);

        //Put the string into the View element to prepare for sending it back out as a View object.
        theTextView.setText(myString);

        //Create a local way to point to the ImageView defined in our layout/xml file.
        //Actually this will be in our local "theView" row.
        ImageView theImageView = (ImageView) theView.findViewById(R.id.imageView1);

        //If one has this attribute setup in the layout file, then it does not need to be established here.
        // However, this would be a better place in the event that each item were to have a different graphic.
        //Not that we can access the image resources here because the "this" context was passed in to the ArrayAdapter.
        theImageView.setImageResource(R.drawable.dot);

        // Keep in mind that this method is called, eventually, by the constructor of ArrayAdapter...So we really have to return the view
        // because it is using this to build the internally generated ViewGroup.
        return theView;
    }
}
