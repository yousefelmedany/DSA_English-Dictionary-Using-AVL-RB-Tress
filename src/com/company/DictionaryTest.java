// package com.company;

// import static java.lang.Math.log;
// import static org.junit.Assert.*;

// import java.io.IOException;

// import org.junit.Test;

// class DictionaryTest {


//     @Test
//     void testSizeAfterAdding197DifferentElementIn4000Words() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         int added = dictionary.BatchInsert("F:\\Trialforjava.txt");
//         dictionary.ends();
//         System.out.println(dictionary.getSize());
//         assertEquals(added, dictionary.getSize());
// //        Dictionary dictionary = new Dictionary("AVL");
// //        int added = dictionary.BashInsert("F:\\Trialforjava.txt");
// //        dictionary.ends();
// //        System.out.println(dictionary.getSize());
// //        assertEquals(added, dictionary.getSize());
//     }

//     @Test // Case of insert one node in tree then remove it, search for it & get size before after
//     void testCornerCase1() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         dictionary.insert("Test");
//         assertEquals(1,dictionary.getSize());
//         dictionary.delete("Test");
//         assertEquals(0,dictionary.getSize());
// //        Dictionary dictionary1 = new Dictionary("AVL");
// //        dictionary.insert("Test");
// //        assertEquals(1,dictionary.getSize());
// //        dictionary.delete("Test");
// //        assertEquals(0,dictionary.getSize());
//     }
//     @Test // Adding the same node 4000 times checking height and size
//     void testCornerCaseTwo() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         for(int i = 0; i < 4000; i++)
//         {
//             dictionary.insert("a");
//         }
//         assertEquals(1, dictionary.getSize());
//         assertEquals(1, dictionary.getHeight());
// //        Dictionary dictionary = new Dictionary("AVL");
// //        for(int i = 0; i < 4000; i++)
// //        {
// //            dictionary.insert("a");
// //        }
// //        assertEquals(1, dictionary.getSize());
// //        assertEquals(1, dictionary.getHeight());
//     }

//     @Test // Batch inserting same Data
//     void batchInsertingTheSameData() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         int added = dictionary.BatchInsert("F:\\200.txt");
//         assertEquals(200, added);
//         int added2 = dictionary.BatchInsert("F:\\200.txt");
//         assertEquals(0, added2);
// //        Dictionary dictionary2 = new Dictionary("AVL");
// //        int added = dictionary.BashInsert("F:\\200.txt");
// //        assertEquals(200, added);
// //        int added2 = dictionary.BashInsert("F:\\200.txt");
// //        assertEquals(0, added2);
//     }

//     @Test // Batch inserting different 200, then deleting only 100 of them, checking size, ToBeDone insha'allah
//     void batchInsert200ThenBatchDeleteHalfOfThem() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         int added = dictionary.BatchInsert("F:\\200.txt");
//         assertEquals(200, added);
//         int deleted = dictionary.BatchDelete("F:\\100.txt");
//         assertEquals(added-deleted, dictionary.getSize());
// //        Dictionary dictionary2 = new Dictionary("AVL");
// //        int added = dictionary.BashInsert("F:\\200.txt");
// //        assertEquals(200, added);
// //        int deleted = dictionary.BashDelete("F:\\100.txt");
// //        assertEquals(added-deleted, dictionary.getSize());
//     }

//     // Checking Height is less than 2lgn in RB, less than lgn+1 in AVL
//     @Test
//     void checkHeightTestCase() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         int added = dictionary.BatchInsert("F:\\200.txt");
//         System.out.println(dictionary.getHeight());
//         System.out.println(2*(log(dictionary.getSize())/log(2)));
//         Assertions.assertTrue(dictionary.getHeight()<=2*(log(dictionary.getSize())/log(2)));
// //        Dictionary dictionary2 = new Dictionary("AVL");
// //        int added = dictionary.BashInsert("F:\\200.txt");
// //        System.out.println(dictionary.getHeight());
// //        System.out.println(2*(log(dictionary.getSize())/log(2)));
// //        Assertions.assertTrue(dictionary.getHeight()<=log(dictionary.getSize())/log(2)+1);
//     }

//     @Test
//     void deletingSameData200TimesAfterAddingIt() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         dictionary.insert("0");
//         int removed = dictionary.BatchDelete("F:\\0.txt");
//         assertEquals(1, removed);
// //        Dictionary dictionary2 = new Dictionary("AVL");
// //        dictionary.insert("0");
// //        int removed = dictionary.BashDelete("F:\\0.txt");
// //        assertEquals(1, removed);
//     }
//     @Test
//     void testing_The_Time_In_Insertion_Deletion_Searching() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         dictionary.BatchInsert("F:\\200.txt");
//         String x = "a";
//         for(int i = 0; i < 200; i++)
//         {
//             dictionary.search(x);
//             x += "a";
//         }
//         //dictionary.BashDelete("F:\\200.txt");
//         dictionary.ends();
//         // NO assert here
// //        Dictionary dictionary2 = new Dictionary("AVL");
// //        dictionary.BashInsert("F:\\200.txt");
// //        dictionary.ends();
//     }


//     @Test
//     void Searching_ForExisting_Non_Existing_Node() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         dictionary.insert("a");
//         assertTrue(dictionary.search("a"));
// //        Dictionary dictionary2 = new Dictionary("AVL");
// //        dictionary.insert("a");
// //        Assertions.assertTrue(dictionary.search("a"));

//     }

//     @Test
//     void Searching_Non_Existing_Node() throws IOException {
//         Dictionary dictionary = new Dictionary("RB");
//         dictionary.insert("a");
//         dictionary.delete("a");
//         Assertions.assertFalse(dictionary.search("a"));
// //        Dictionary dictionary2 = new Dictionary("RB");
// //        dictionary.insert("a");
// //        dictionary.delete("a");
// //        Assertions.assertFalse(dictionary.search("a"));
//     }
//         // Running out of ideas el sara7, na2es 4 3ala el 20, matroken l 25oya el m7nksh
//         // 4aylen kimo tarek l 3oza, 20 test cases al7amdullilah

// }