    
    //variable
    
    FileAdapter adapter;
    ArrayList<File> fileDataArrayList = new ArrayList<File>();
    
    private File filePathImageCamera;
    private static final int IMAGE_GALLERY_REQUEST = 1;
    private static final int IMAGE_CAMERA_REQUEST = 2;
    private static final int FILE_REQUEST = 3;
    
    String imageFilePath;
    
    
    //methods
    
    private void selectImage() {
    
            final CharSequence[] options = {"Take Photo", "Add Photos", "Add Files", "Cancel"};
            
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Upload Document!");
            builder.setItems(options, new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int item) {

                    if (item == 0) {
                        photoCameraIntent();
                    } else if (item == 1) {
                        photoGalleryIntent();
                    } else if (item == 2) {
                        fileIntent();
                    } else {
                        dialog.dismiss();
                    }

                }

            });
       
            builder.show();
    }
    
    private void fileIntent() {
        Intent filesIntent;
        filesIntent = new Intent(Intent.ACTION_GET_CONTENT);
        
        //if you want pick multiple files
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            filesIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        filesIntent.addCategory(Intent.CATEGORY_OPENABLE);
        filesIntent.setType("*/*");  //use image/* for photos, etc.
        getActivity().startActivityFromFragment(this, filesIntent, FILE_REQUEST);
    }
    
    private void photoCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;

        try {
            filePathImageCamera = createImageFile();
        } catch (IOException ex) {
            // Error occurred while creating the File

        }


        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                //Create a file to store the image

                if (filePathImageCamera != null) {
                    Uri photoURI = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".provider", filePathImageCamera);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            photoURI);
                    getActivity().startActivityFromFragment(this, pictureIntent,
                            IMAGE_CAMERA_REQUEST);
                }


            } else {


                if (filePathImageCamera != null) {
                    uri = Uri.fromFile(filePathImageCamera);

                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(pictureIntent, IMAGE_CAMERA_REQUEST);
                }


            }
        }

    }
    
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Create the storage directory if it does not exist
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            Log.d("create dir", "failed to create directory");
        }

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void photoGalleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityFromFragment(this, Intent.createChooser(intent, "Select Picture"), IMAGE_GALLERY_REQUEST);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IMAGE_CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (filePathImageCamera != null && filePathImageCamera.exists()) {

                    fileDataArrayList.add(filePathImageCamera);
                    adapter.notifyDataSetChanged();

                } else {

                }
            }
        } else if (requestCode == FILE_REQUEST || requestCode == IMAGE_GALLERY_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (null != data) { // checking empty selection
                    if (null != data.getClipData()) { // checking multiple selection or not
                        for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                            Uri uri = data.getClipData().getItemAt(i).getUri();
                            fileIntoList(uri);
                        }
                    } else {
                        Uri uri = data.getData();
                        fileIntoList(uri);
                    }
                }
            }
        }

    }

    private void fileIntoList(Uri uri) {
        try {
            File file = FileUtil.from(mContext, uri);
            fileDataArrayList.add(file);
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

