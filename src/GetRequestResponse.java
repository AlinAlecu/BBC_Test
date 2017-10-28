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
        Result += AddNameValueItem("Url", FUrl);
        if( FError.compareTo("") == 0 )
        {
            Result += AddNameValueItem("Status_Code", FStatusCode );
            Result += AddNameValueItem("Content_Length", FContentLength);
            Result += AddNameValueItem("Date", FDate.toString(), true);
        }
        else
        {
            Result += AddNameValueItem("Error", FError, true);
        }

        Result +=  "\n}";

        return Result;
    }

    // add non ending name value pair as json. value is int
    private String AddNameValueItem( final String Name, final int Value)
    {
        return AddNameValueItem( Name, Value, false );
    }

    // add non ending name value pair as json. value is string
    private String AddNameValueItem( final String Name, final String Value)
    {
        return AddNameValueItem( Name, Value, false );
    }

    // add name value pair as json. values is string
    private String AddNameValueItem( final String Name, final String Value, boolean FinalItem)
    {
        String Result = new String();

        Result += ("\n  \"" + Name + "\": \"" + Value + "\"");
        Result += AddComma( FinalItem );

        return Result;
    }

    // add name value pair as json. values is int
    private String AddNameValueItem( final String Name, final int Value, boolean FinalItem )
    {
        String Result = new String();

        Result += "\n  \"" + Name + "\": " + Value;
        Result += AddComma( FinalItem );

        return Result;
    }

    // return comma based on param
    private String AddComma( boolean FinalElement )
    {
        if( !FinalElement )
        {
            return ",";
        }
        return "";
    }

    private String FUrl;
    private int FStatusCode;
    private int FContentLength;
    private Date FDate;
    private String FError;

}
