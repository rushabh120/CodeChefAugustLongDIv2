//https://www.codechef.com/AUG19B/problems/ZOMCAV
//for the main logic of the program, skip to line 197

import java.io.*;
import java.util.*;
import java.lang.*;

public class ZombieAndCaves implements Runnable
{
    static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars==-1)
                throw new InputMismatchException();

            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                }
                catch (IOException e)
                {
                    throw new InputMismatchException();
                }

                if(numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
        public int nextInt()
        {
            int c = read();

            while(isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-')
            {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do
            {
                if(c<'0'||c>'9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.')
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.')
            {
                c = read();
                double m = 1;
                while (!isSpaceChar(c))
                {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }
    public static void main(String args[]) throws Exception
    {
        new Thread(null, new GudduAndMother(),"Main",1<<27).start();
    }

    public void run()
    {
        InputReader in = new InputReader(System.in);
        PrintWriter w = new PrintWriter(System.out);

        int t = in.nextInt();
        while(t-->0){

            int n = in.nextInt();
            int c[] = new int[n];
            int h[] = new int[n];
            int a[] = new int[n];
            int d[] = new int[n+1];

            for (int i=0;i<n;i++){
                c[i] = in.nextInt();
            }

            for (int i=0;i<n;i++){
                h[i] = in.nextInt();
            }

            //logic to increase the radiation level in the caves in O(n) time complexity
            for (int i=0;i<n;i++){
                int c1 = i-c[i] ;
                int c2 = i+c[i] ;

                if (c1<0)
                    c1=0;
                if (c2>n-1)
                    c2=n-1;

                d[c1] += 1;
                d[c2 + 1] -= 1;

            }
            for (int i=0;i<n;i++){
                if (i==0)
                    a[i]=d[i];
                else {
                    a[i]=d[i]+a[i-1];
                }
            }

            //checking if the radiation could kill all the zombies
            Arrays.sort(a);
            Arrays.sort(h);
            int kill=0;
            for (int i=0;i<n;i++){
                if (a[i]==h[i])
                    kill=1;
                else {
                    kill = 0;
                    break;
                }
            }

            if (kill==1)
                w.println("YES");
            else
                w.println("NO");

        }
        w.flush();
        w.close();
    }
}

