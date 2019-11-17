package jc.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * client of ssh
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class SSHClient {

	// public Scpclient(){}
	static private SSHClient instance;

	static synchronized public SSHClient getInstance(String IP, int port, String username, String passward) {
		if (instance == null) {
			instance = new SSHClient(IP, port, username, passward);
		}
		return instance;
	}

	private SSHClient(String IP, int port, String username, String passward) {
		this.ip = IP;
		this.port = port;
		this.username = username;
		this.password = passward;
	}
	
	public Connection getConn() {
		Connection conn = new Connection(ip, port);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username, password);
			if (isAuthenticated == false) {
				System.err.println("authentication failed");
			}
			return conn;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SCPClient getSCPClient() {
		Connection conn = new Connection(ip, port);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username, password);
			if (isAuthenticated == false) {
				System.err.println("authentication failed");
			}
			return new SCPClient(conn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Session getSession() {
		try {
			Connection conn = getConn();
			return conn.openSession();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("resource")
	public String sshCommand(String commandLine) {
		StringBuilder sb = new StringBuilder();
		try {
			Session session = getSession();
			session.execCommand(commandLine);
	        //获取返回输出
			InputStream stdout = new StreamGobbler(session.getStdout());
	        //返回错误输出
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
			String line;
			while ((line = stdoutReader.readLine())!=null) {
				line = stdoutReader.readLine();
				sb.append(line);
			}
	        //关闭Session
			session.close();
			return sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @Remark 
	 * @Demo client.putFile("/Desktop/report/fin20190829.xls", "/home");
	 * @param localFile
	 * @param remoteTargetDirectory
	 */
	public void putFile(String localFile, String remoteTargetDirectory) {
		Connection conn = new Connection(ip, port);
		try {
			SCPClient client = getSCPClient();
			client.put(localFile, remoteTargetDirectory, "0755");
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String ip;
	private int port;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
