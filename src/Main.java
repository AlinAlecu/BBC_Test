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
                break;
            case normal:
                // scanner for input
                Scanner Scan = new Scanner(System.in);

                // intro message
                String Message = "enter website and press enter or paste newline separated list:";
                System.out.println(Message);

                // get input line
                String Input = Scan.nextLine();

                // list of response details
                ArrayList<GetRequestResponse> Response = new ArrayList<GetRequestResponse>();

                // while still parsing lines
                while (!Input.isEmpty())
                {
                    // add new GetRequestResponse to store information in
                    Response.add( new GetRequestResponse());

                    // send Get request and populate storing object
                    BBC_Test.sendGet(Input, Response.get(Response.size() - 1));

                    //System.out.println(Message);

                    // get input line
                    Input = Scan.nextLine();
                }

                // output results
                for(GetRequestResponse Element : Response)
                {
                    System.out.print(Element.toString());
                }
                break;
        }
    }

    // parse argument list
    public static void ParseParams( String[] args )
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
    private void sendGet( String Website, GetRequestResponse Output )
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
    private void ProcessError( GetRequestResponse Output, final String Website, Exception E )
    {
        System.err.println( "\nFailed GET request for Url " + Website + "( " + E.toString() + " )!");

        Output.SetUrl(Website);
        Output.SetError( E.toString() );
    }
}
