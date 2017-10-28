import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

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

                // arraylist of json document pe url processing
                ArrayList<StringBuffer> Output = new ArrayList<StringBuffer>();

                // while still parsing lines
                while (!Input.isEmpty())
                {
                    // add new strinbuffer to store information in
                    Output.add( new StringBuffer());

                    // send Get request and populate string buffer
                    BBC_Test.sendGet(Input, Output.get(Output.size() - 1));

                    //System.out.println(Message);

                    // get input line
                    Input = Scan.nextLine();
                }

                // output results
                for(StringBuffer Element : Output)
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
    private void sendGet( String Website, StringBuffer Output )
    {
        try {
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

            // create element for site
            StringBuffer Element = new StringBuffer();

            Element.append("\n{");
            AddNameValueItem("Url", Website, Element);
            AddNameValueItem("Status_Code", ResponseCode, Element);
            AddNameValueItem("Content_Length", ContentLength, Element);
            AddNameValueItem("Date", TimeStamp.toString(), Element, true);
            Element.append("\n}");

            // add to document
            Output.append(Element);
        }
        // could do manual url validation but we have the api doing this for us.
        // we just catch and report the errors
        catch(Exception E)
        {
            ProcessError( Output, Website, E );
        }
    }

    // add non ending name value pair as json. value is int
    private void AddNameValueItem( final String Name, final int Value, StringBuffer Out)
    {
        AddNameValueItem( Name, Value, Out, false );
    }

    // add non ending name value pair as json. value is string
    private void AddNameValueItem( final String Name, final String Value, StringBuffer Out)
    {
        AddNameValueItem( Name, Value, Out, false );
    }

    // add name value pair as json. values is string
    private void AddNameValueItem( final String Name, final String Value, StringBuffer Out, boolean FinalItem)
    {
        Out.append("\n  \"" + Name + "\": \"" + Value + "\"");
        AddComma( Out, FinalItem );
    }

    // add name value pair as json. values is int
    private void AddNameValueItem( final String Name, final int Value, StringBuffer Out, boolean FinalItem )
    {
        Out.append("\n  \"" + Name + "\": " + Value);
        AddComma( Out, FinalItem );
    }

    // add comma to stringbuffer based on param
    private void AddComma( StringBuffer Out, boolean FinalElement )
    {
        if( FinalElement )
        {
            Out.append(",");
        }
    }

    // process error message
    private void ProcessError( StringBuffer Output, final String Website, Exception E )
    {
        System.err.println( "\nFailed GET request for Url " + Website + "( " + E.toString() + " )!");

        StringBuffer Element = new StringBuffer();

        Element.append("\n{");
        AddNameValueItem("Url", Website, Element);
        AddNameValueItem("Error", E.toString(), Element, true);
        Element.append("\n}");

        // add to document
        Output.append(Element);
    }
}
