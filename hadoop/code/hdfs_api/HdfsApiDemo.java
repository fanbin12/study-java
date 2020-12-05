package cn.itcast.hdfs_api;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.AssumptionViolatedException;
import org.junit.Test;

import javax.print.DocFlavor;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HdfsApiDemo {
    @Test
    public void urlHdfs() throws IOException {
        //注册url
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        //获取hdfs文件输入流
        InputStream inputStream = new URL("hdfs://node01:8020/a.txt").openStream();
        //获取输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\hello.txt"));
        IOUtils.copy(inputStream, fileOutputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);
    }

    //获取文件系统方法
    //1
    @Test
    public void getFileSysterm() throws IOException {
        //1.创建configure对象
        Configuration configuration = new Configuration();
        configuration.set("fs.defalut","hdfs:node01:8020");

        //2.设置文件系统类型
        //3.获取指定的文件系统
        FileSystem fileSystem = FileSystem.get(configuration);
        System.out.println(fileSystem);

    }

    @Test
    public void  getFileSysterm2() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        System.out.println(fileSystem);

    }

    @Test
    public void getFilessysterm3() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defalut","hdfs:node01:8020");
        FileSystem fileSystem = FileSystem.newInstance(configuration);
        System.out.println(fileSystem);
    }

    @Test
    public void getFileSysterm4() throws IOException, URISyntaxException {
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020"), new Configuration());
        System.out.println(fileSystem);
    }

    //遍历获取文件目录
    @Test
    public void listFiles() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"),new Configuration());
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path("/"), true);
        while (locatedFileStatusRemoteIterator.hasNext()){
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println(next.getPath()+"......"+next.getPath().getName());
            BlockLocation[] blockLocations = next.getBlockLocations();
            System.out.println(blockLocations.length);
            //打印不了空文件
        }
    }

    @Test
    public void mkdirs() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        boolean mkdirs = fileSystem.mkdirs(new Path("/hello/mkdir/test"));
        fileSystem.close();
    }

    //下载文件
    @Test
    public void getFileToLocal() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        FSDataInputStream open = fileSystem.open(new Path("/a.txt")); //获取源文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("e:\\timaer.txt"));
        int copy = IOUtils.copy(open, fileOutputStream);
        System.out.println(".........");
        System.out.println(copy);
        IOUtils.closeQuietly(open);
        IOUtils.closeQuietly(fileOutputStream);
        fileSystem.close();
    }

    //上传wenjian
    @Test
    public void putData() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        fileSystem.copyFromLocalFile(new Path("file:///e:\\timaer.txt"),
        new Path("/hello"));
        fileSystem.close();
    }

    @Test
    public void mergeFile() throws URISyntaxException, IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration(), "root");
        FSDataOutputStream outputStream = fileSystem.create(new Path("/bigfile.txt"));
        LocalFileSystem local = FileSystem.getLocal(new Configuration());
        FileStatus[] fileStatuses = local.listStatus(new Path("file:///E:"));
        for (FileStatus fileStatus : fileStatuses) {
            FSDataInputStream inputStream = local.open(fileStatus.getPath());
            IOUtils.copy(inputStream, outputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
        IOUtils.closeQuietly(outputStream);
        local.close();

    }
}
