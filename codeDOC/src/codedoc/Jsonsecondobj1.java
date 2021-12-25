
package codedoc;

import java.util.List;

public class Jsonsecondobj1 {
    Integer contestId ;
        
    String index ;
    String name ;
    String type ;
    List <String> tags;
    public Jsonsecondobj1 (Integer contestId,String problemsetName , String index , String name , String type ,Float points,Integer rating , List <String> tags)
    {
       this.contestId=contestId ;
    this.index= index;
    this.name=name ;
    this.type=type ;
    this.tags =tags; 
    }
}
