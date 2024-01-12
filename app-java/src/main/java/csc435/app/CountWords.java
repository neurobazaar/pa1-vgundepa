package csc435.app;

public class CountWords
{
    public long dataset_size = 0;
    public double execution_time = 0.0;

    public void count_words(String input_dir, String output_dir)
    {
        // TO-DO implement count words logic
    }

    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.err.println("improper number of arguments");
            System.exit(1);
        }

        CountWords countWords = new CountWords();

        countWords.count_words(args[0], args[1]);
        
        System.out.print("Finished counting " + countWords.dataset_size + " MiB of words");
        System.out.println(" in " + countWords.execution_time + " miliseconds");
    }
}