package javas;

import java.util.*;
import java.util.regex.*;

/**
 * Created by andreas on 2018-08-20.
 */
public class TagContentExtractor {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
       // Stack<Tag> stack = new Stack<>();
        Queue<Tag> list = new LinkedList<>();

        while(testCases>0){
            String line = in.nextLine();
            String tagExpression = "(<[^>]+>)";
            Pattern p = Pattern.compile(tagExpression);
            Matcher m = p.matcher(line);

            boolean gotTags = false;

            while (m.find()) {
                String tag = m.group();
                int startIdx = m.start();
                int endIdx = m.end() - 1;
                boolean isEndTag = tag.contains("/");
                Tag t = new Tag(tag, startIdx, endIdx, isEndTag);
                list.add(t);

            }

            while(list.size() > 1) {
                Tag t1 = list.poll();
                Tag t2 = list.peek();

                if(t2.isEndTag() && !t1.isEndTag() && t1.equals(t2)) {
                    String s = line.substring(t1.getEndIdx()+1, t2.getStartIdx());
                    if(s.length() > 0) {
                        System.out.println(s);
                        gotTags = true;
                    }
                }
            }

            if(!gotTags) {
                System.out.println("None");
            }

            testCases--;
        }
    }
}

class Tag {
    private String tag;
    private int startIdx;
    private int endIdx;
    private boolean isEndTag;

    public Tag(String tag, int startIdx, int endIdx, boolean isEndTag) {
        this.tag = tag;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.isEndTag = isEndTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag1 = (Tag) o;

        return tag != null ? tag.replace("/", "").equals(tag1.getTag().replace("/", "")) : tag1.tag == null;

    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + startIdx;
        result = 31 * result + endIdx;
        result = 31 * result + (isEndTag ? 1 : 0);
        return result;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag='" + tag + '\'' +
                ", startIdx=" + startIdx +
                ", endIdx=" + endIdx +
                ", isEndTag=" + isEndTag +
                '}';
    }

    public int getStartIdx() {
        return startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public boolean isEndTag() {
        return isEndTag;
    }
}