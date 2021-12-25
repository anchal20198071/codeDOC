
package codedoc;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.*;
public class CodeforcesProblem {
    String s1="" , s2="";
	private final String HTTP = "http://";
	private final String PROBLEMURL = "codeforces.com/problemset/problem/:c/:p";
        String url ;
        String problemId;
        Document doc;
	//private final String GYMPROBLEMURL = "codeforces.com/gym/:c/problem/:p";

	
	public String fetchProblemStatement() {

            /*-
             * Codeforces problem page look like this
             *  <div class="problemindexholder" problemindex="B">
             *  		<div class="ttypography">
             *  			<div class="problem-statement"> ..... </div>
             * 			</div>
             * 	</div>
             */
            // fetch the page containing the problem statement
            //System.out.println(doc); // e.printStackTrace();
            // remove html entities from the code
            
            Elements problems = doc.getElementsByTag("p");
            //Elements problem = problems.getElementsByTag("pre");
//problems.getElementByTag("pre");

System.out.println("codeforces: fetched problem " + problemId);

String problemstatements="";
for(Element e:problems)
    problemstatements=problemstatements+e.html()+"\n\n";

//problemstatements=problemstatements.replaceAll(".","\n");
		return problemstatements;
	}
        
public String fetchInput() {

            return s1;
		
	}
public String fetchOutput() {

            
return s2;
		
	}
public String fetchName() {

            Elements problems = doc.getElementsByClass("title");
            
System.out.println("codeforces: fetched problem " + problemId);
//Element p=null;
for(Element e:problems)
{
    return e.html();
}
return "";
		
	}
	
	public String getUrl() {
		if (url == null || url.isEmpty()) {
			String[] id = problemId.split("-");
			String problemUrl = PROBLEMURL;
			

			// replace ":c" with the contest id(like
			problemUrl = problemUrl.replace(":c", id[0]);

			// replace ":p" with the problem id (like A,B,C etc)
			problemUrl = problemUrl.replace(":p", id[1]);

			url = HTTP + problemUrl;
			//this.setUrl(url);
		}
		return url;
	}

	public Document getdoc()
        {
            return doc;
        }
	public String getProblemId() {
		if (problemId == null || problemId.isEmpty()) {
			String u = getUrl();
			u = u.replace("codeforces.com/problemset/problem/", "");
			u = u.replace("/", "-");
			//this.setProblemId(u);
		}
		return this.problemId;
	}

	public CodeforcesProblem(String problemId, String url) throws IOException {
		this.problemId=problemId;
                this.url=url;
                this.url = getUrl();
                doc = Jsoup.connect(this.url).timeout(10000000).get();
                
                System.out.println(doc);
                doc.outputSettings().escapeMode(EscapeMode.xhtml);
                Elements problems = doc.getElementsByTag("pre");
            

int k=0;

for(Element e:problems)
{
    if(k%2==0)
    {s1=s1+e.html() +"\n";}
    else
    {s2=s2+e.html()+"\n";}
     
    k++;
}
s1=s1.replaceAll(" ", "\n");
	}

	

}

