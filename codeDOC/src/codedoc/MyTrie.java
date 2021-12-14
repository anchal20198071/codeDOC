package codedoc;
import java.util.*;

public class MyTrie
{
    
    static MyTrie currnode=null;
    Map<Character, MyTrie> map;
    Map <String , Integer> currentwords;
    int currentstrength=0;
    MyTrie parent=null;
    
    public MyTrie()
            {
               parent=null;
                currentwords=new HashMap<>();
                map = new HashMap<>();
            }
     Map <String , Integer> searcher(char ch)
    {
        
      String s1[]={};
        if(currnode==null)
            currnode=this;
        
        Map<Character, MyTrie> mp=currnode.getMap();
        if(mp.get(ch)==null)
        {
            currnode=this;
            return null;
        }
         
        
        currnode=mp.get(ch);
        
       return  mp.get(ch).currentwords;
        
    }
    void inserter(String word)
    {
        Map<Character, MyTrie> temp = map;
        int n = word.length();
        MyTrie currentparent=this;
        for(int i=0;i<n;i++) {
            char ch = word.charAt(i);
            MyTrie l;
            if(!temp.containsKey(ch)) {
                l=new MyTrie();
                temp.put(ch, l);
                l.parent=currentparent;
            }
            else
            {
                l=temp.get(ch);
            }
            
            if(!l.currentwords.containsKey(word))
            l.currentwords.put(word, i);
            
            l.currentstrength++;
            System.out.println( l.currentstrength);
           currentparent=l;
            temp = temp.get(ch).getMap();
        }
        temp.put('*', null);
    }
    void initialwords()
    {
        String word[]={"int" , "include" ,"import"};
        for(int i=0 ;i<word.length;i++)
        {
            inserter(word[i]);
        }
    }
    Map<Character, MyTrie> getMap() {
        return map;
    }
}
