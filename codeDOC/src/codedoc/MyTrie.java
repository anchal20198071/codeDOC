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
     Map <String , Integer> searcher(char ch, MyTrie trieobj)
    {
        
      String s1[]={};
      
        if((currnode==null && NewJPanel.firstword==true) || (NewJPanel.currpos==1))
            currnode=trieobj;
        else if(currnode==null)
            return null;
        
        Map<Character, MyTrie> mp=currnode.getMap();
        currnode=mp.get(ch);
        if(mp.get(ch)==null)
        {
            return null;
        }
        
        
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
            //System.out.println( l.currentstrength);
           currentparent=l;
            temp = temp.get(ch).getMap();
        }
        temp.put('*', null);
    }
    void initialwords()
    {
        String word[]={"int","float" , "include" ,"import" ,"auto","break" ,"case","char","const","continue","default",
            "do","double","else" ,"enum","extern","int","long","register","return","short","signed","sizeof","static","struct",	"switch" ,"typedef" ,"union","unsigned","void" ,"volatile","while"};
        


        for(int i=0 ;i<word.length;i++)
        {
            inserter(word[i]);
        }
        
        
    }
    Map<Character, MyTrie> getMap() {
        return map;
    }
}
