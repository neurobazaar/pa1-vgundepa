package csc435.app;

public class SortWords
{
    public long num_words = 0;
    public double execution_time = 0.0;

    public void sort_words(String input_dir, String output_dir)
    {
        // TO-DO implement sort words logic
    }

    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.err.println("improper number of arguments");
            System.exit(1);
        }

        SortWords sortWords = new SortWords();

        sortWords.sort_words(args[0], args[1]);
        
        System.out.print("Finished sorting " + sortWords.num_words + " words");
        System.out.println(" in " + sortWords.execution_time + " miliseconds");
    }
}