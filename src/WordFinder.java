import java.util.ArrayList;

public class WordFinder
{
    String text;
    ArrayList<ArrayList<String>> sentences;
    ArrayList<String> words;

    public WordFinder(String text)
    {
        this.text = text;
        sentences = new ArrayList<ArrayList<String>>();
        words = new ArrayList<String>();
    }

    public void FindThreeWordsStartWithUppercase()
    {
        int i = 0;
        for(String sentence : text.split("\\.")) {
            sentences.add(new ArrayList<String>());
            for(String word: sentence.split(" "))
            {
                if(word.length() >= 1)
                    if(Character.toUpperCase(word.charAt(0)) == word.charAt(0))
                    {
                        words.add(word);
                        if(words.size() == 3)
                        {
                            for(String w: words)
                            {
                                sentences.get(i).add(w);
                            }
                            words.clear();
                        }
                        continue;
                    }
                words.clear();

            }
            System.out.print("Sentence: ");
            System.out.println(sentence);
            System.out.println("Triplets of words: ");
            int j = 0;
            for(String w: sentences.get(i)) {
                ++j;
                System.out.println(w);
                if(j == 3)
                {
                    j = 0;
                    System.out.println();
                }
            }
            System.out.println();

            i++;
        }


    }
}
