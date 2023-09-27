public class ViDuJDBC {
   public static void main(String[] args) {
      // Lay date va time chuan
      java.util.Date javaDate = new java.util.Date();
      long javaTime = javaDate.getTime();
      System.out.println("Java Date la:" + 
             javaDate.toString());

      //Lay va hien thi SQL DATE
      java.sql.Date sqlDate = new java.sql.Date(javaTime);
      System.out.println("SQL DATE la: " + 
             sqlDate.toString());

      //Lay va hien ti SQL TIME
      java.sql.Time sqlTime = new java.sql.Time(javaTime);
      System.out.println("SQL TIME la: " + 
             sqlTime.toString());
      //Lay va hien thi SQL TIMESTAMP
      java.sql.Timestamp sqlTimestamp =
      new java.sql.Timestamp(javaTime);
      System.out.println("SQL TIMESTAMP la: " + 
             sqlTimestamp.toString());
     }// Ket thuc main
}//Ket thuc SqlDateTime