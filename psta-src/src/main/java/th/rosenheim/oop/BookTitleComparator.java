package th.rosenheim.oop;
import java.util.Comparator;

/**
 * @author Chuan Hn Wong - 1084380
 * Comparator for comparing books by title (case-insensitive)
 */
public class BookTitleComparator implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2){
        return b1.getTitle().compareToIgnoreCase(b2.getTitle());
    }
}
