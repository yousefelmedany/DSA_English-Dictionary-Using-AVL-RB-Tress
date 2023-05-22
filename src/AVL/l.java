// package AVL;

// public class l {
//     @Override
//     public void insert(T node) throws IOException {
//         long start = System.currentTimeMillis();

//         System.out.println("Insert in AVL: "+node);
//         //
//         long end = System.currentTimeMillis();
//         insertionTimeStr+=("Took: " +(end-start) + " ms\n");
//     }

//     @Override
//     public void delete(T node) throws IOException {
//         long start = System.currentTimeMillis();
//         System.out.println("Delete in AVL element: "+node);
//         long end = System.currentTimeMillis();
//         insertionTimeStr+=("Took: " +(end-start) + " ms\n");
//     }

//     @Override
//     public void search(T node) {
//         System.out.println("Search in AVL for "+node);
//     }



//     @Override
//     public int getSize() {
//         System.out.println("Getsize in AVL");
//         return 0;
//     }

//     @Override
//     public int getHeight() {
//         System.out.println("GetHeight in AVL");
//         return 0;
//     }
//     @Override
//     public void ends() throws IOException {
//         FileWriter insertionTime, deletionTime;
//         System.out.println("Writing ");

//         insertionTime = new FileWriter("insertion_in_avl.txt");

//         insertionTime.write(insertionTimeStr);
//         System.out.println("Writing done");

//         insertionTime.close();
//         deletionTime = new FileWriter("deletion_in_avl.txt");
//         deletionTime.write(deletionTimeStr);
//         deletionTime.close();
//     }
    
// }
