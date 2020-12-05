# 两台阿里云服务器搭建Hadoop服务器

设备：两台阿里云服务器，xshell,xftp

## 下载Hadoop并上传

1.下载Hadoop（Hadoop3.X）

hadoop各组件软件下载地址：

清华镜像：https://mirrors.tuna.tsinghua.edu.cn/apache/



2.使用xftp上传到其中一台服务器并解压到自己的路径

3.配置/etc/hosts文件

```
vim /etc/hosts
```

配置自己集群的IP和域名，注意：本服务器的IP只能是阿里云的内网地址，其他IP是阿里云服务器的公网IP

4.配置文件：（在Hadoop3.X/etc/hadoop路径下）

修改core-site.xml文件

```
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->

<!-- Put site-specific property overrides in this file. -->

<configuration>
	<!--  指定集群的文件系统类型:分布式文件系统 -->
	<property>
		<name>fs.default.name</name>
		<value>hdfs://node01:8020</value>
	</property>
	<!--  指定临时文件存储目录 -->
	<property>
		<name>hadoop.tmp.dir</name>
		<value>/export/servers/hadoop-2.7.5/hadoopDatas/tempDatas</value>
	</property>
	<!--  缓冲区大小，实际工作中根据服务器性能动态调整 -->
	<property>
		<name>io.file.buffer.size</name>
		<value>4096</value>
	</property>

	<!--  开启hdfs的垃圾桶机制，删除掉的数据可以从垃圾桶中回收，单位分钟 -->
	<property>
		<name>fs.trash.interval</name>
		<value>10080</value>
	</property>
</configuration>

```

修改hdfs-site.xml:(注意xml文件中，Hadoop的版本是2.X，所以要根据自己Hadoop路径修改，本文中所有文件都要修改)

```
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->

<!-- Put site-specific property overrides in this file. -->

<configuration>

	 <property>
			<name>dfs.namenode.secondary.http-address</name>
			<value>node01:50090</value>
	</property>

	<!-- 指定namenode的访问地址和端口 -->
	<property>
		<name>dfs.namenode.http-address</name>
		<value>0.0.0.0:50070</value>
	</property>
	<!-- 指定namenode元数据的存放位置 -->
	<property>
		<name>dfs.namenode.name.dir</name>
		<value>file:///export/servers/hadoop-2.7.5/hadoopDatas/namenodeDatas,file:///export/servers/hadoop-2.7.5/hadoopDatas/namenodeDatas2</value>
	</property>
	<!--  定义dataNode数据存储的节点位置，实际工作中，一般先确定磁盘的挂载目录，然后多个目录用，进行分割  -->
	<property>
		<name>dfs.datanode.data.dir</name>
		<value>file:///export/servers/hadoop-2.7.5/hadoopDatas/datanodeDatas,file:///export/servers/hadoop-2.7.5/hadoopDatas/datanodeDatas2</value>
	</property>
	
	<!-- 指定namenode日志文件的存放目录 -->
	<property>
		<name>dfs.namenode.edits.dir</name>
		<value>file:///export/servers/hadoop-2.7.5/hadoopDatas/nn/edits</value>
	</property>
	

	<property>
		<name>dfs.namenode.checkpoint.dir</name>
		<value>file:///export/servers/hadoop-2.7.5/hadoopDatas/snn/name</value>
	</property>
	<property>
		<name>dfs.namenode.checkpoint.edits.dir</name>
		<value>file:///export/servers/hadoop-2.7.5/hadoopDatas/dfs/snn/edits</value>
	</property>
	<!-- 文件切片的副本个数-->
	<property>
		<name>dfs.replication</name>
		<value>3</value>
	</property>

	<!-- 设置HDFS的文件权限-->
	<property>
		<name>dfs.permissions</name>
		<value>false</value>
	</property>

	<!-- 设置一个文件切片的大小：128M-->
	<property>
		<name>dfs.blocksize</name>
		<value>134217728</value>
	</property>
</configuration>

```

修改mapred-site.xml

```
<configuration>
	<!-- 指定分布式计算使用的框架是yarn -->
	<property>
			<name>mapreduce.framework.name</name>
			<value>yarn</value>
	</property>

	<!-- 开启MapReduce小任务模式 -->
	<property>
		<name>mapreduce.job.ubertask.enable</name>
		<value>true</value>
	</property>
	
	<!-- 设置历史任务的主机和端口 -->
	<property>
		<name>mapreduce.jobhistory.address</name>
		<value>node01:10020</value>
	</property>

	<!-- 设置网页访问历史任务的主机和端口 -->
	<property>
		<name>mapreduce.jobhistory.webapp.address</name>
		<value>node01:19888</value>
	</property>
</configuration>

```

修改：yarn-site.xml

```
<configuration>
	<!-- 配置yarn主节点的位置 -->
	<property>
		<name>yarn.resourcemanager.hostname</name>
		<value>node01</value>
	</property>

	<property>
		<name>yarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
	</property>
	
	<!-- 开启日志聚合功能 -->
	<property>
		<name>yarn.log-aggregation-enable</name>
		<value>true</value>
	</property>
	<!-- 设置聚合日志在hdfs上的保存时间 -->
	<property>
		<name>yarn.log-aggregation.retain-seconds</name>
		<value>604800</value>
	</property>
	<!-- 设置yarn集群的内存分配方案 -->
	<property>    
		<name>yarn.nodemanager.resource.memory-mb</name>    
		<value>20480</value>
	</property>

	<property>  
        	 <name>yarn.scheduler.minimum-allocation-mb</name>
         	<value>2048</value>
	</property>
	<property>
		<name>yarn.nodemanager.vmem-pmem-ratio</name>
		<value>2.1</value>
	</property>

</configuration>

```

修改Hadoop-env.sh和mapred-env,sh中添加jdk:

```
 export JAVA_HOME=/export/servers/jdk1.8.0_141 
```

修改work文件：

 node01 （自己的IP映射名）

node02 

最后创建文件夹：

```
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/tempDatas
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/namenodeDatas
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/namenodeDatas2
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/datanodeDatas
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/datanodeDatas2
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/nn/edits
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/snn/name
mkdir -p /export/servers/hadoop-2.7.5/hadoopDatas/dfs/snn/edits
```

再将第一台机器的Hadoop移植到第二台：

```
cd /export/servers/（hadoop的安装路径）
scp -r hadoop-2.7.5 node02:$PWD
```

配置Hadoop环境变量：

```
vim /etc/profile
export HADOOP_HOME=/export/servers/hadoop-2.7.5
export PATH=:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH
```

```
source /etc/profile
```

然后再配置：安装目录下/sbin/start-dfs.sh和stop-dfs.sh加上：

```
HDFS_DATANODE_USER=root
HDFS_SECURE_DN_USER=hdfs
HDFS_NAMENODE_USER=root
HDFS_SECONDARYNAMENODE_USER=root
```

安装目录下/sbin/start-yarn.sh和stop-yarn.sh加上：

```
YARN_RESOURCEMANAGER_USER=root
HADOOP_SECURE_DN_USER=yarn
YARN_NODEMANAGER_USER=root
```

启动：

```
cd /export/servers/hadoop-2.7.5/
bin/hdfs namenode -format //格式化，只使用一次
sbin/start-dfs.sh
sbin/start-yarn.sh
sbin/mr-jobhistory-daemon.sh start historyserver
```

使用jps查看打开的进程：

```
[root@node01 sbin]# jps
4786 JobHistoryServer
23860 QuorumPeerMain
4228 ResourceManager
4358 NodeManager
10262 DataNode
11434 Jps
10124 NameNode
10476 SecondaryNameNode
```

 三个端口查看界面

 http://node01:50070 查看hdfs 

http://node01:8088 查看yarn集群

 http://node01:19888 查看历史完成的任务  

## 遇到的问题解决：

1.50070端口被一直占用：

hosts文件下本服务器IP应该为内网IP



2.hdfs启动报错：

然后再配置：安装目录下/sbin/start-dfs.sh和stop-dfs.sh加上：

```
HDFS_DATANODE_USER=root
HDFS_SECURE_DN_USER=hdfs
HDFS_NAMENODE_USER=root
HDFS_SECONDARYNAMENODE_USER=root
```

安装目录下/sbin/start-yarn.sh和stop-yarn.sh加上：

```
YARN_RESOURCEMANAGER_USER=root
HADOOP_SECURE_DN_USER=yarn
YARN_NODEMANAGER_USER=root
```



3.在web段访问不了50070端口：

https://blog.csdn.net/lihaohang6/article/details/94591198