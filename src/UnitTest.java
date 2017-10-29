import java.util.Date;

public class UnitTest
{
    public static void Run()
    {
        Test_CreateNameValueItem_StringVsIntValue();
        Test_CreateComma();
        Test_ResponseToStringError();
        Test_ResponseToStringNoError();
        Test_GetRequestResponse_SetUrl();
        Test_GetRequestResponse_SetContentLength();
        Test_GetRequestResponse_SetStatusCode();
        Test_GetRequestResponse_SetDate();
    }

    // tests creating json pair when value is int vs string
    // string version adds quotes around value so resulting string length difference should be 2
    private static void Test_CreateNameValueItem_StringVsIntValue()
    {
        String Message = "Test_AddNameValueItem_StringVsIntValue: int version should have no quote marks around value:\n";

        String TestStringIntValue = CommonFunctions.CreateNameValueItem("test1", 1);
        String TestStringString = CommonFunctions.CreateNameValueItem("test1", "1");

        int LengthDiff = TestStringString.length() - TestStringIntValue.length();

        if( LengthDiff == 2 )
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: difference between lengths of resulting strings is not 2( " + LengthDiff +" )\n\n";
        }

        System.out.println( Message );
    }

    private static void Test_CreateComma()
    {
        String Message = "Test_CreateComma: comma should only be added if not last json line\n";

        String NonFinalItemString = CommonFunctions.CreateNameValueItem("test", 1);
        String FinalItemString = CommonFunctions.CreateNameValueItem("test", 1, true);

        int LengthDiff = NonFinalItemString.length() - FinalItemString.length();

        if( LengthDiff == 1 )
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: difference between lengths of resulting strings is not 1( " + LengthDiff +" )\n\n";
        }

        System.out.println( Message );
    }


    private static void Test_ResponseToStringError()
    {
        GetRequestResponseWrapper TestResponse = new GetRequestResponseWrapper();
        TestResponse.SetError("test error");
        TestResponse.SetUrl("test url");

        String Message = "Test_ResponseToStringError: GetRequestResponseWrapper toString should only contain url and error information\n";

        String TestResponseString = TestResponse.toString();

        if( TestResponseString.contains("Error") &&  TestResponseString.contains("Url") &&
                !TestResponseString.contains("Status_Code") &&
                !TestResponseString.contains("Content_Length") &&
                !TestResponseString.contains("Date"))
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: resulting string contains wrong information\n\n";
        }

        System.out.println( Message );
    }

    private static void Test_ResponseToStringNoError()
    {
        GetRequestResponseWrapper TestResponse = new GetRequestResponseWrapper();
        TestResponse.SetContentLength(12345);
        TestResponse.SetStatusCode(200);
        TestResponse.SetUrl("test url");

        String Message = "Test_ResponseToStringNoError: GetRequestResponseWrapper toString should contain url, status code, content length and date information, when no error occurred\n";

        String TestResponseString = TestResponse.toString();

        if( !TestResponseString.contains("Error") &&  TestResponseString.contains("Url") &&
                TestResponseString.contains("Status_Code") &&
                TestResponseString.contains("Content_Length") &&
                TestResponseString.contains("Date"))
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: resulting string contains wrong information\n\n";
        }

        System.out.println( Message );
    }

    private static void Test_GetRequestResponse_SetUrl()
    {
        String Message = "Test_GetRequestResponse_SetUrl: GetRequestResponseWrapper SetUrl should set the Url to specified value\n";

        GetRequestResponseWrapper TestResponse = new GetRequestResponseWrapper();
        String Url = "http://www.test1212.com";
        TestResponse.SetUrl(Url);

        if( TestResponse.GetUrl() == Url )
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: Url not set to specified value\n\n";
        }

        System.out.println( Message );
    }

    private static void Test_GetRequestResponse_SetStatusCode()
    {
        String Message = "Test_GetRequestResponse_SetStatusCode: GetRequestResponseWrapper SetStatusCode should set the StatusCode to specified value\n";

        GetRequestResponseWrapper TestResponse = new GetRequestResponseWrapper();
        int StatusCode = 1313;
        TestResponse.SetStatusCode(StatusCode);

        if( TestResponse.GetStatusCode() == StatusCode )
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: StatusCode not set to specified value\n\n";
        }

        System.out.println( Message );
    }

    private static void Test_GetRequestResponse_SetContentLength()
    {
        String Message = "Test_GetRequestResponse_SetContentLength: GetRequestResponseWrapper SetContentLength should set the ContentLength to specified value\n";

        GetRequestResponseWrapper TestResponse = new GetRequestResponseWrapper();
        int ContentLength = 1313;
        TestResponse.SetContentLength(ContentLength);

        if( TestResponse.GetContentLength() == ContentLength )
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: ContentLength not set to specified value\n\n";
        }

        System.out.println( Message );
    }

    private static void Test_GetRequestResponse_SetDate()
    {
        String Message = "Test_GetRequestResponse_SetDate: GetRequestResponseWrapper SetDate should set the Date to specified value\n";

        GetRequestResponseWrapper TestResponse = new GetRequestResponseWrapper();
        Date DateTest = new Date(1000000);
        TestResponse.SetDate(DateTest);

        if( TestResponse.GetDate() == DateTest )
        {
            Message += "Success!\n\n";
        }
        else
        {
            Message += "Failure: Date not set to specified value\n\n";
        }

        System.out.println( Message );
    }
}
