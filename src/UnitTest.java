public class UnitTest
{
    public static void Run()
    {
        Test_AddNameValueItem_StringVsIntValue();
    }

    // tests creating json pair when value is int vs string
    // string version adds quotes around value so resulting string length difference should be 2
    private static void Test_AddNameValueItem_StringVsIntValue()
    {
        String Message = "Test_AddNameValueItem_StringVsIntValue: ";

        String TestStringIntValue = CommonFunctions.AddNameValueItem("test1", 1);
        String TestStringString = CommonFunctions.AddNameValueItem("test1", "1");

        int LengthDiff = TestStringString.length() - TestStringIntValue.length();

        if( LengthDiff == 2 )
        {
            Message += "Success!";
        }
        else
        {
            Message += "Failure: difference between lengths of resulting strings is not 2( " + LengthDiff +" )";
        }

        System.out.println( Message );
    }
}
