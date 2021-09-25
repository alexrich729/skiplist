import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    @org.junit.jupiter.api.Test
    void genericTest() {
        SkipList sl = new SkipList();
        sl.insert(1);
        System.out.println(sl.toStringFull());
        System.out.println(sl);
        sl.insert(3);
        System.out.println(sl.toStringFull());
        System.out.println(sl);
        sl.insert(5);
        System.out.println(sl.toStringFull());
        System.out.println(sl);
        sl.insert(7);
        System.out.println(sl.toStringFull());
        System.out.println(sl);
        sl.insert(13);
        System.out.println(sl.toStringFull());
        System.out.println(sl);
        sl.insert(24);
        System.out.println(sl.toStringFull());
        System.out.println(sl);
        sl.insert(24);
        sl.delete(24);
        sl.delete(1000);
        sl.delete(3);
        sl.delete(1);
        //System.out.println("Searched: " + sl.search(24).getItem());
        //System.out.println("Searched: " + sl.search(7).getItem());
        System.out.println(sl.toStringFull());
        System.out.println(sl);
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void search() {
    }

    @org.junit.jupiter.api.Test
    void insert() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }
}