import java.util.Date;

public class GetRequestResponseWrapper
{
    public GetRequestResponseWrapper()
    {
        FDate = new Date();
        FUrl = new String();
        FError = new String();
        FStatusCode = 0;
        FContentLength = 0;
    }

    public GetRequestResponseWrapper(String UrlIn, int StatusCodeIn, int ContentLength, Date Date)
    {
        FUrl = UrlIn;
        FStatusCode = StatusCodeIn;
        FContentLength = ContentLength;
        FDate = Date;
        FError = new String();
    }

    public String GetUrl()
    {
      return FUrl;
    }

    public int GetStatusCode()
    {
        return FStatusCode;
    }

    public int GetContentLength()
    {
        return FContentLength;
    }

    public Date GetDate()
    {
        return FDate;
    }

    public String GetError()
    {
        return FError;
    }

    public void SetUrl( String UrlIn )
    {
        FUrl = UrlIn;
    }

    public void SetStatusCode( int StatusCodeIn )
    {
        FStatusCode = StatusCodeIn;
    }

    public void SetContentLength( int ContentLengthIn )
    {
        FContentLength = ContentLengthIn;
    }

    public void SetDate( Date DateIn )
    {
        FDate = DateIn;
    }

    public void SetError( String ErrorIn )
    {
        FError = ErrorIn;
    }

    public String toString()
    {
        String Result = new String();

        Result += "\n{";
        Result += "\n   " + CommonFunctions.CreateNameValueItem("Url", FUrl);
        if( FError.compareTo("") == 0 )
        {
            Result += "\n   " + CommonFunctions.CreateNameValueItem("Status_Code", FStatusCode );
            Result += "\n   " + CommonFunctions.CreateNameValueItem("Content_Length", FContentLength);
            Result += "\n   " + CommonFunctions.CreateNameValueItem("Date", FDate.toString(), true);
        }
        else
        {
            Result += "\n   " + CommonFunctions.CreateNameValueItem("Error", FError, true);
        }

        Result +=  "\n}";

        return Result;
    }

    // private variables
    private String FUrl;
    private int FStatusCode;
    private int FContentLength;
    private Date FDate;
    private String FError;

}
