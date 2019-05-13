
package Testing;

import Model.Document;
import Model.InvertedIndex;
import Model.Posting;
import java.io.File;
import java.util.ArrayList;

public class TestReadDirectory {
    public static void main(String[] args) {
        File dir = new File("Dokumen");
        InvertedIndex index = new InvertedIndex();
        index.readDirectory(dir);
        ArrayList<Document> listDoc = index.getListOfDocument();
        for (int i = 0; i < listDoc.size(); i++) {
            Document doc = listDoc.get(i);
            System.out.println("Content : " + doc.getId());
            System.out.println(doc.getContent());
        }
        index.MakeDictionaryWithTermNumber();
        for (int i = 0; i < listDoc.size(); i++) {
            listDoc.get(i).IndonesiaStemming();
            index.MakeTFIDF(i);
        }
//        for (int i = 0; i < listDoc.size(); i++) {
        for (int i = 0; i < listDoc.size(); i++) {
            ArrayList<Posting> post = index.MakeTFIDF(i);
            for (int j = 0; j < post.size(); j++) {
                System.out.println(post.get(j).toString());
            }
        }

        for (int i = 1; i < listDoc.size() ; i++) {
            for (int j = i + 1; j < listDoc.size()+1; j++) {
                ArrayList<Posting> post1 = index.MakeTFIDF(i);
                ArrayList<Posting> post2 = index.MakeTFIDF(j);
                double Cosine = index.getInnerProduct(post1, post2);
                System.out.println("Hasil Cosine Similarity dari doc"+i+ " dan doc"+j+"= " + Cosine);

            }
        }

//        }
    }
}
