public class AppLifeApp extends Application  {

    private static AppLifeApp mInstance;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        MultiDex.install(this);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        ScaleConfig.create(this,
                1080, // Design Width
                1920, // Design Height
                3,    // Design Density
                3,    // Design FontScale
                ScaleConfig.DIMENS_UNIT_DP);

    }

    public static AppLifeApp get() {
        return mInstance;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public static synchronized AppLifeApp getInstance() {
        return mInstance;
    }
    
}
