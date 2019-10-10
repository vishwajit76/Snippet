    public static String getDate(long timestamp, String format) {
        SimpleDateFormat sfd = new SimpleDateFormat(format);
        return sfd.format(new Date(timestamp));
    }
 
    public static String getDate(long timestamp) {
        SimpleDateFormat sfd = new SimpleDateFormat("MMM dd, yyyy");
        return sfd.format(new Date(timestamp));
    }
 
    public static String parseDate(String inputDateString, SimpleDateFormat inputDateFormat, SimpleDateFormat outputDateFormat) {
        Date date = null;
        String outputDateString = null;
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }
    
    public static String getLongDate(String date) {

        String formatedDate = date;
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("dd MMM, yyyy");
        formatedDate = parseDate(date, ymdFormat, EEEddMMMyyyy);

        return formatedDate;

    }
    
    public static String getDateFormat(String date, boolean isReverse) {

        String formatedDate = date;
        SimpleDateFormat ymdFormat = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("yyyy-MM-dd");

        if (isReverse) {
            formatedDate = parseDate(date, EEEddMMMyyyy, ymdFormat);
        } else {
            formatedDate = parseDate(date, ymdFormat, EEEddMMMyyyy);
        }

        return formatedDate;
    }
    
    public static String getTimeAgoDate(long pastTimeStamp) {
        // for 2 min ago   use  DateUtils.MINUTE_IN_MILLIS
        // for 2 sec ago   use  DateUtils.SECOND_IN_MILLIS
        // for 1 hours ago use  DateUtils.HOUR_IN_MILLIS

        long now = System.currentTimeMillis();

        if (now - pastTimeStamp < 1000) {
            pastTimeStamp = pastTimeStamp + 1000;
        }
        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(pastTimeStamp, now, DateUtils.SECOND_IN_MILLIS);
        return ago.toString();
    }
    
    public static Boolean isSameDay(long mPreviousTimestamp, long latestTimestamp) {

        boolean isSameDay = false;

        Date d = new Date(mPreviousTimestamp);
        Date d2 = new Date(latestTimestamp);
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        isSameDay = c.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);

        return isSameDay;

    }
