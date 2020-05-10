给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。

请你返回让 s 成为回文串的 最少操作次数 。

「回文串」是正读和反读都相同的字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int minInsertions(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        for(int j=1;j<n;j++){
            dp[j-1][j]=s.charAt(j-1)==s.charAt(j)?0:1;
            for(int i=j-2;i>=0;i--){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i][j-1],dp[i+1][j])+1;
                }
            }
        }
        return dp[0][n-1];
    }
}

给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回符合要求的最少分割次数。
class Solution {
    public int minCut(String s) {
        int n=s.length();
        int[] dp=new int[n];
        boolean[][] isPalid=new boolean[n][n];
        for(int i=0;i<n;i++){
            dp[i]=n;
            for(int j=0;j<=i;j++){
                if(s.charAt(i)==s.charAt(j)&&(i-j<=1||isPalid[j+1][i-1])){
                    dp[i]=j==0?0:Math.min(dp[j-1]+1,dp[i]);
                    isPalid[j][i]=true;
                }
            }
        }
        return dp[n-1];
    }
}

public class Main{
    private static void reverseSentence(String s){
        char[] arr=s.toCharArray();
        reverse(arr,0,arr.length-1);
        int i=0;
        for(int j=0;j<=arr.length;j++){
            if(j==arr.length||arr[j]==' '){
                reverse(arr,i,j-1);
                i=j+1;
            }
        }
        System.out.println(new String(arr));
    }
    private static void reverse(char[] arr,int left,int right){
        while(left<right){
            char c=arr[left];
            arr[left++]=arr[right];
            arr[right--]=c;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.nextLine();
            reverseSentence(s);
        }
    }
}

public class Main {
    private static class Node {
        int val;
        int timeStamp;

        public Node(int val, int timeStamp) {
            this.val = val;
            this.timeStamp = timeStamp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int timeStamp = 0;
        Map<String, Node> map = new HashMap<>();
        while (sc.hasNext()) {
            String name = sc.next();
            int line = sc.nextInt();
            int index = name.lastIndexOf('\\');
            String s = name + " " + line;
            if (index == -1) {
                if (map.containsKey(s)) {
                    Node pre = map.get(s);
                    Node node = new Node(pre.val + 1, pre.timeStamp);
                    map.put(s, node);
                } else {
                    Node node = new Node(1, timeStamp++);
                    map.put(s, node);
                }
            } else {
                String tmp = s.substring(index + 1);
                if (map.containsKey(tmp)) {
                    Node pre = map.get(tmp);
                    Node node = new Node(pre.val + 1, pre.timeStamp);
                    map.put(tmp, node);
                } else {
                    Node node = new Node(1, timeStamp++);
                    map.put(tmp, node);
                }
            }

        }

        String[] arr = new String[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            arr[i++] = key;
        }
        Arrays.sort(arr, (o1, o2) -> {
            Node n1 = map.get(o1);
            Node n2 = map.get(o2);
            if (n1.val != n2.val) return n2.val - n1.val;
            return n1.timeStamp - n2.timeStamp;
        });
        for (int j = 0; j < arr.length && j < 8; j++) {
            String[] str = arr[j].split(" ");
            int len = str[0].length();
            if (len > 16) {
                System.out.println(str[0].substring(len - 16) + " " + str[1] + " " + map.get(arr[j]).val);
            } else {
                System.out.println(arr[j] + " " + map.get(arr[j]).val);
            }
        }
    }
}


