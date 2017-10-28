import java.util.Date;

public class GetRequestResponse
{
    public GetRequestResponse()
    {
        FDate = new Date();
        FUrl = new String();
        FError = new String();
        FStatusCode = 0;
        FContentLength = 0;
    }

    public GetRequestResponse(String UrlIn, int StatusCodeIn, int ContentLength, Date Date)
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
        Result += "\n   " + CommonFunctions.AddNameValueItem("Url", FUrl);
        if( FError.compareTo("") == 0 )
        {
            Result += "\n   " + CommonFunctions.AddNameValueItem("Status_Code", FStatusCode );
            Result += "\n   " + CommonFunctions.AddNameValueItem("Content_Length", FContentLength);
            Result += "\n   " + CommonFunctions.AddNameValueItem("Date", FDate.toString(), true);
        }
        else
        {
            Result += "\n   " + CommonFunctions.AddNameValueItem("Error", FError, true);
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
