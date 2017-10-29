public class CommonFunctions
{
    // add non ending name value pair as json. value is int
    public static String CreateNameValueItem(final String Name, final int Value)
    {
        return CreateNameValueItem( Name, Value, false );
    }

    // add non ending name value pair as json. value is string
    public static String CreateNameValueItem(final String Name, final String Value)
    {
        return CreateNameValueItem( Name, Value, false );
    }

    // add name value pair as json. values is string
    public static String CreateNameValueItem(final String Name, final String Value, boolean FinalItem)
    {
        String Result = new String();

        Result += ("\"" + Name + "\": \"" + Value + "\"");
        Result += CreateComma( FinalItem );

        return Result;
    }

    // add name value pair as json. values is int
    public static String CreateNameValueItem(final String Name, final int Value, boolean FinalItem )
    {
        String Result = new String();

        Result += "\"" + Name + "\": " + Value;
        Result += CreateComma( FinalItem );

        return Result;
    }

    // return comma based on param
    private static String CreateComma(boolean FinalElement )
    {
        if( !FinalElement )
        {
            return ",";
        }
        return "";
    }
}
