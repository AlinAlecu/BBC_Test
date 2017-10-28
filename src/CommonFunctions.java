public class CommonFunctions
{
    // add non ending name value pair as json. value is int
    public static String AddNameValueItem( final String Name, final int Value)
    {
        return AddNameValueItem( Name, Value, false );
    }

    // add non ending name value pair as json. value is string
    public static String AddNameValueItem( final String Name, final String Value)
    {
        return AddNameValueItem( Name, Value, false );
    }

    // add name value pair as json. values is string
    public static String AddNameValueItem( final String Name, final String Value, boolean FinalItem)
    {
        String Result = new String();

        Result += ("\"" + Name + "\": \"" + Value + "\"");
        Result += AddComma( FinalItem );

        return Result;
    }

    // add name value pair as json. values is int
    public static String AddNameValueItem( final String Name, final int Value, boolean FinalItem )
    {
        String Result = new String();

        Result += "\"" + Name + "\": " + Value;
        Result += AddComma( FinalItem );

        return Result;
    }

    // return comma based on param
    private static String AddComma( boolean FinalElement )
    {
        if( !FinalElement )
        {
            return ",";
        }
        return "";
    }
}
