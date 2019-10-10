  private void initSpinner() {
        CustomSpinnerAdapter spinnerArrayAdapter = new CustomSpinnerAdapter(mContext, R.layout.item_spinner, R.id.text1, getResources().getStringArray(R.array.spinner_array));
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(spinnerArrayAdapter);


        spinner_doc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               //doSomethingYouWant


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
