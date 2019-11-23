package com.lyh.gmail.user.demo;


public class Demo {
    private static int run(int n){
        if(n == 1 || n ==2){
            return 1;
        }else {
            return run(n-1) + run(n-2);
        }
    }

    private static int checkUtil(int[] arry,int start,int end){
        int mid = arry[start];
        int left = start;
        int right = end;


        while (right>left){
            while (right>left){
                if(arry[right]<mid){
                    arry[left]=arry[right];
                    break;
                }
                right--;
                System.out.println("循环：right"+right);
            }

            while (right>left){
                if(arry[left]>=mid){
                    arry[right]=arry[left];
                    break;
                }
                left++;
                System.out.println("循环：left"+left);
            }

        }
        arry[left] = mid;


        return left;
    }



    public static void main(String[] args) {
        int n=4;
        System.out.println(run(n));
        int[] arry = new int[]{5,3,8,7,1,8,3,4,6,2,7,9,1,9,10};
        checkUtil(arry,0,14);
        for (int i = 0; i <arry.length; i++) {
            System.out.print("第"+i+"位："+arry[i]+"\t");
        }
    }
}
