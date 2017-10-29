import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // create class instance
        Main BBC_Test = new Main();

        // parse params for flags
        ParseParams( args );

        // check mode of operation
        switch(Constants.Mode)
        {
            case debug:
                UnitTest.Run();
                break;
            case normal:
                // scanner for input
                Scanner Scan = new Scanner(System.in);

                // list of response details
                ArrayList<GetRequestResponseWrapper> ResponseList = new ArrayList<GetRequestResponseWrapper>();
                HashMap<Integer, Integer> StatusCodesMap = new HashMap<Integer, Integer>();

                // while still parsing lines
                while (Scan.hasNextLine())
                {
                    // get input line
                    String Input = Scan.nextLine();

                    // create new response object
                    GetRequestResponseWrapper CurrentResponse = new GetRequestResponseWrapper();

                    // add new GetRequestResponseWrapper to store information in
                    ResponseList.add( CurrentResponse);

                    // send Get request and populate storing object
                    BBC_Test.sendGet(Input, CurrentResponse);

                    // process code for appearances
                    int StatusCode = CurrentResponse.GetStatusCode();

                    // check if code is present or not and update map
                    ProcessCode( StatusCode, StatusCodesMap );
                }

                // output results
                Output( ResponseList, StatusCodesMap );

                break;
        }
    }

    // checks if code is in map
    private static void ProcessCode( int StatusCode, HashMap<Integer, Integer> StatusCodesMap )
    {
        // valid url check
        if( StatusCode != 0 )
        {
            // if one item already found
            if (StatusCodesMap.containsKey(StatusCode))
            {
                // status code exists already
                StatusCodesMap.put(StatusCode, StatusCodesMap.get(StatusCode) + 1);
            }
            else
            {
                // new status code
                StatusCodesMap.put(StatusCode, 1);
            }
        }
    }

    // checks if code is in map
    private static void Output(ArrayList<GetRequestResponseWrapper> Response, HashMap<Integer, Integer> StatusCodesMap )
    {
        // output results
        System.out.println("Results: ");
        for(GetRequestResponseWrapper Element : Response)
        {
            System.out.print( Element.toString() );
        }

        // summary
        System.out.println("\n\nSummary: ");
        String Summary = "\n[";
        Iterator CodesIterator = StatusCodesMap.entrySet().iterator();
        while (CodesIterator.hasNext())
        {
            Map.Entry pair = (Map.Entry)CodesIterator.next();
            Summary += "\n  {";
            Summary += "\n      " + CommonFunctions.CreateNameValueItem("Status_Code", (Integer)pair.getKey());
            Summary += "\n      " + CommonFunctions.CreateNameValueItem("Number_of_responses", (Integer)pair.getValue(), true);
            Summary += "\n  }";
            if( CodesIterator.hasNext() )
            {
                Summary += ",";
            }
            CodesIterator.remove(); // avoids a ConcurrentModificationException
        }

        Summary += "\n]";
        System.out.println(Summary);
    }

    // parse argument list
    private static void ParseParams( String[] args )
    {
        // check for flags
        if( args.length > 0 )
        {
            String Param1 = args[0];

            if( Param1.compareTo("-debug") == 0 )
            {
                Constants.Mode = RunMode.debug;
                System.out.println("Running in debug mode!");
            }
        }
    }

    // HTTP GET request
    private void sendGet( String Website, GetRequestResponseWrapper Output )
    {
        try
        {
            URL Obj = new URL(Website);
            HttpURLConnection Con = (HttpURLConnection) Obj.openConnection();

            // connection and read timeouts
            Con.setConnectTimeout(Constants.RequestTimeoutMillies);
            Con.setReadTimeout(Constants.RequestReadMillies);

            // optional default is GET
            Con.setRequestMethod("GET");

            int ResponseCode = Con.getResponseCode();
            int ContentLength = Con.getContentLength();
            long DateValue = Con.getDate();
            Date TimeStamp = new Date(DateValue);

            Output.SetUrl( Website );
            Output.SetStatusCode( ResponseCode );
            Output.SetContentLength( ContentLength );
            Output.SetDate( TimeStamp );
        }
        // could do manual url validation but we have the api doing this for us.
        // we just catch and report the errors
        catch(Exception E)
        {
            ProcessError( Output, Website, E );
        }
    }

    // process error message
    private void ProcessError(GetRequestResponseWrapper Output, final String Website, Exception E )
    {
        System.err.println( "Failed GET request for Url " + Website + "( " + E.toString() + " )!");

        Output.SetUrl(Website);
        Output.SetError( E.toString() );
    }
}
