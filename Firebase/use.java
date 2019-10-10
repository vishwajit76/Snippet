  
  //init
  Query query = AppUtils.getDocumentsRef(d_id);
  ClassSnapshotParser parser = new ClassSnapshotParser<Document>(Document.class);
  FilterableFirebaseArray filterableFirebaseArray = new FilterableFirebaseArray(query, parser);
  
  
  //if you want exclude item from firebaserRecyclerView then
  filterableFirebaseArray.addExclude("Your Firebase Reference Child Key");
  
  
  //if you want to remove excluded key then
  filterableFirebaseArray.removeExclude("Your Firebase Reference Child Key");
  
  
  //use into FirebaseRecyclerOptions
  FirebaseRecyclerOptions<Document> options =
                new FirebaseRecyclerOptions.Builder<Document>()
                        .setSnapshotArray(filterableFirebaseArray)
                        .setLifecycleOwner(this)
                        .build();

   adapter = new DocAdapter(options);
   recyclerView.setAdapter(adapter);
