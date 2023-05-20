package com.study.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码示例  压缩数据
 * @author yan
 */
public class HuffmanCode {
    public static void main(String[] args) {

//        String srcFile = "D:\\11.txt";
//        String dstFile = "D:\\11.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩完成！");

        String zipFile = "D:\\11.zip";
        String dstFile = "D:\\112.txt";
        unZipFile(zipFile,dstFile);
        System.out.println("解压完成！");

        /**
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();

        byte[] huffmanZip = huffmanZip(bytes);
        System.out.println("huffmanZip = " + Arrays.toString(huffmanZip) + " huffmanZip length = " + huffmanZip.length);

        byte[] decode = decode(huffmanCodes,huffmanZip);
        System.out.println("decode = " + new String(decode));
         */
        /**
        List<Node> nodes = getNodes(bytes);
        System.out.println("nodes = " + nodes);
        Node node = listToHuffmanTree(nodes);
        node.preOrder();

        // 通过赫夫曼树 获取 赫夫曼编码
        Map<Byte, String> codes = getCodes(node);
        System.out.println("赫夫曼编码 ： " + codes);

        byte[] zip = zip(bytes, codes);
        System.out.println("zip = " + Arrays.toString(zip) + " zip length = " + zip.length);
        */

    }

    /**
     * 文件解压
     */
    public static void unZipFile(String zipFile, String dstFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(decode);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            try{
                ois.close();
                is.close();
                os.close();
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * 文件压缩
     * @param srcFile 需要压缩的文件路径
     * @param dstFile 压缩后存放的文件路径
     */
    public static void zipFile(String srcFile, String dstFile){
        // 创建 输入 输出 流
        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            is = new FileInputStream(srcFile);
            byte[] isByte = new byte[is.available()];
            is.read(isByte);
            System.out.println(isByte.length);
            byte[] bytes = huffmanZip(isByte);
            System.out.println(bytes.length);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(bytes);
            oos.writeObject(huffmanCodes);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        } finally {
          try{
              is.close();
              os.close();
              oos.close();
          } catch (IOException ex){
              System.out.println(ex.getMessage());
          }
        }
    }

    /**
     * 赫夫曼 解码
     * @param huffmanCodes 赫夫曼编码
     * @param bytes 需要解码的byte数组
     * @return 解码后的 byte 数组
     */
    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] bytes){
        Map<String,Byte> reversalCodes = new HashMap<>();
        // 将赫夫曼编码的 key : val 反转 为 key = val : val = key
        for (Map.Entry<Byte, String> byteStringEntry : huffmanCodes.entrySet())
            reversalCodes.put(byteStringEntry.getValue(),byteStringEntry.getKey());
        // 解码 转换 二进制字符串 并且拼接在一起
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = i == (bytes.length - 1);
            stringBuilder.append(byteToBitStr(!flag, bytes[i]));
        }
        // 用于存储解压缩后的byte数据
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();){
            int count = 1;
            while (true){
                Byte aByte = reversalCodes.get(stringBuilder.substring(i, i + count));
                if (aByte != null){
                    byteList.add(aByte);
                    break;
                }
                count++;
            }
            i+=count;
        }
        // 将List<Byte> 转换为 byte[] 然后返回
        byte[] reversalByte = new byte[byteList.size()];
        for (int i = 0; i < reversalByte.length; i++) reversalByte[i] = byteList.get(i);
        return reversalByte;
    }

    public static byte[] huffmanZip(byte[] bytes){
        // 将 byte数组转换为 Node 节点 存储
        List<Node> nodes = getNodes(bytes);
        // 将转换的 Node 节点生成为 哈夫曼数
        Node node = listToHuffmanTree(nodes);
        // 通过赫夫曼数 获取 赫夫曼编码
        Map<Byte, String> codes = getCodes(node);
        // 通过赫夫曼编码 压缩原始的 byte数组
        byte[] zip = zip(bytes, codes);
        return  zip;
    }

    /**
     * 将通过赫夫曼压缩后的byte数组 解压成 通过赫夫曼编码的二进制的字符串
     * @param bytes 通过赫夫曼压缩后的 byte 数组
     * @return 赫夫曼编码的二进制字符串
     */
    private static String byteToBinarySystemStr(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes){

        }
        return "";
    }

    /**
     * 将byte 转换为二进制字符串
     * @param flag 是否需要补码 true 需要补码  false 不需要补码
     * @param b 传入需要转换的 byte
     * @return b 对应的二进制字符串（注意是补码后的）
     */
    private static String byteToBitStr(boolean flag, byte b){
        int temp = b;
        if (flag){
            temp |= 256; // 256 => 1 0000 0000
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }

    /**
     * 将byte 转换为二进制字符串 这个是按照 一个 byte 8个位数来算的  不太符合当前需求
     * @param b 传入需要转换的 byte
     * @return
     */
    public static String getBinaryStrFromByte(byte b) {
        String result = "";
        byte a = b;
        for (int i = 0; i < 8; i++) {
            byte c = a;
            a = (byte) (a >> 1);//每移一位如同将10进制数除以2并去掉余数。
            a = (byte) (a << 1);
            if (a == c) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            a = (byte) (a >> 1);
        }
        return result;
    }

    /**
     * 通过赫夫曼编码压缩数据
     * @param bytes 原始的 byte 数组
     * @param codes 通过赫夫曼数 获取的赫夫曼编码
     * @return 压缩后的byte数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte,String> codes){
        StringBuilder sb = new StringBuilder();
        // 获取通过赫夫曼编码转换后的 字符串
        for (byte aByte : bytes) {
            sb.append(codes.get(aByte));
        }
        // 按照每个 byte 8个位数 来获取该字符串需要分成几段
        int len = (sb.length() + 7) / 8;
        // 用于存储压缩后的数据
        byte[] zipCode = new byte[len];
        for (int i = 0,index = 0; i < sb.length(); i+= 8,index++) {
            String str;
            if (i+8 > sb.length()) str = sb.substring(i);
            else str = sb.substring(i, i+8);
            zipCode[index] = (byte) Integer.parseInt(str,2);
        }
        return zipCode;
    }

    /**
     * 根据赫夫曼数 生成对应的赫夫曼编码  使用 Map<Byte,String> 进行存储
     * 往左代表 0 右代表 1
     * 示例：
     *      赫夫曼编码 ： {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     */
    // 用于存储 赫夫曼数 生成的 赫夫曼编码
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    // 用于拼接赫夫曼编码
    static StringBuilder stringBuilder = new StringBuilder();
    // 重载getCodes 方法 便于调用
    private static Map<Byte,String> getCodes(Node root){
        if (root == null) throw new NullPointerException();
        getCodes(root,"",stringBuilder);
        return huffmanCodes;
    }
    private static void getCodes(Node root, String code, StringBuilder sb){
        StringBuilder builder = new StringBuilder(sb);
        builder.append(code);
        // 如果 root 节点的 b元素不为null 则代表当前节点为叶子节点
        if (root.getB() != null){
            huffmanCodes.put(root.getB(),builder.toString());
        }else {
            // 往左递归 左用 0 表示
            getCodes(root.getLeft(),"0",builder);
            // 往右递归 右用 1 表示
            getCodes(root.getRight(),"1",builder);
        }
    }

    // 将文本转换成的byte数组转换为List<Node>
    private static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();

        Map<Byte,Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            Integer integer = map.get(aByte);
            if (integer == null) map.put(aByte,1);
            else map.put(aByte,++integer);
        }

        for (Byte aByte : map.keySet()) {
            nodes.add(new Node(aByte,map.get(aByte)));
        }

        return nodes;
    }

    // 将List<Node> 转换成哈夫曼数
    private static Node listToHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parNode.setLeft(leftNode);
            parNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parNode);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {

    private Byte b;
    private int weight;

    private Node left;
    private Node right;

    @Override
    public String toString() {
        return "Node{" +
                "b=" + b +
                ", weight=" + weight +
                '}';
    }

    public Node(Byte b, int weight) {
        this.b = b;
        this.weight = weight;
    }

    public Byte getB() {
        return b;
    }

    public void setB(Byte b) {
        this.b = b;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    protected void preOrder(){
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大  大到小相反 o - this
        return this.getWeight() - o.getWeight();
    }
}
