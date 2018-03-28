//2014.6.22.2版，2014年6月22日第2版
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
//import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JScrollBar;
import javax.swing.JTextField;
public class MainProgram_2 extends JFrame implements ActionListener
{
	
	//登陆界面代码start
	JFrame jf1;//登陆界面
	JPanel jpl1;//jf1中的整体布局
	JPanel jpl2;//jf1中的上方布局
	JPanel jpl3;//jf1中的中方布局
	JPanel jpl3_temp;//jpl3中的临时布局，用于解决jtf1和jpf1中的text区域被无限放大的问题
	JPanel jpl4;//jf1中的下方布局
	JButton jbt1;//jf1中的确认
	JButton jbt2;//jf1中的重置
	JButton jbt3;//jf1中的退出
	JLabel jlb1;//用户名
	JLabel jlb2;//密码
	JLabel jlb3;//提示输入用户名和密码
	JTextField jtf1;//用户名
	JPasswordField jpf1;//密码
	String[] UserName=new String[100];
	String[] UserPassword=new String[100];
    int TotalOfUser=3;
	//登陆界面代码end
    
    //Server和Client选择界面代码start
    JFrame jf2;//Server和Client的选择界面
    JPanel jpl5;//jf2中的整体布局
    JPanel jpl6;//jf2中的上方布局
    JPanel jpl7;//jf2中的中间布局
    JPanel jpl8;//jf2中的下方布局
    JComboBox jcb1;//Server和Client的选择
    String[] com=new String[2];//选择列表项目数组
    JButton jbt4;//jf2中的确认
    JButton jbt5;//jf2中的退出
    //Server和Client选择界面代码end
    
    //Server界面代码start
    String LocalIP;//记录本地IP
    JFrame jf3;//Server界面
    JPanel jpl9;//jf3整体布局
    JPanel jpl11;//jpl9中的上方布局
    JPanel jpl13;//jpl11中的上方布局
    JPanel jpl14;//jpl11中的下方布局
    JPanel jpl17;//jpl9的中间布局
    JPanel jpl18;//jpl9的下方布局
    JPanel jpl19;//jpl17的上方布局
    JPanel jpl20;//jpl17的下方布局
    JButton jbt7;//开启多人群聊功能
    JButton jbt8;//保存聊天记录到本地
    JButton jbt9;//发送/接收文件
    JButton jbt10;//发送信息
    JTextArea jta1;//已接收的信息
    JTextArea jta2;//待发送的信息
    JScrollPane jsp1;//已接收信息的滚动条
	JScrollPane jsp2;//待发送信息的滚动条
	JButton jbt14;//客户端连接完成后的按键
	static String ServerSendString="";//Server发送的字符串
	static String ServerReceiveString="";//Server接收的字符串
	DataInputStream ServerISM;//Server读取信息数据流
	DataOutputStream ServerOSM;//Server写出信息数据流
	JFileChooser ServerJFC;//Server发送文件界面
	File ServerF;//Server发送的文件
	Thread stm;//Server接收信息线程
	Thread stf;//Server接收文件线程
  	//Server界面代码end
  		
  	//Client界面代码start
    JFrame jf4;//Client界面
    JPanel jpl10;//jf4整体布局
    JPanel jpl12;//jpl10中的上方布局
    JPanel jpl15;//jpl12中的上方布局
    JPanel jpl16;//jpl12中的下方布局
    JTextField jtf2;//输入服务器IP
    JButton jbt6;//连接服务器
    JPanel jpl21;//jpl10的中间布局
    JPanel jpl22;//jpl10的下方布局
    JPanel jpl23;//jpl21的上方布局
    JPanel jpl24;//jpl21的下方布局
    JButton jbt11;//保存聊天记录到本地
    JButton jbt12;//发送/接收文件
    JButton jbt13;//发送信息
    JTextArea jta3;//已接收的信息
    JTextArea jta4;//待发送的信息
    JScrollPane jsp3;//已接收信息的滚动条
	JScrollPane jsp4;//待发送信息的滚动条
	static String ClientSendString="";//Client发送的字符串
	static String ClientReceiveString="";//Client接收的字符串
	DataInputStream ClientISM;//Client读取信息数据流
	DataOutputStream ClientOSM;//Client写出信息数据流
	JFileChooser ClientJFC;//Client发送文件界面
	File ClientF;//Client发送的文件
	Thread ctm;//Client接收信息线程
	Thread ctf;//Client接收文件线程
  	//Client界面代码end
	
	//Server连接代码start
	ServerSocket ServerSS;//Server端的Socket监听
	Socket ServerS;//Server端的Socket
	//Server连接代码end
	
    //Client连接代码start
	Socket ClientS;//Client端 的Socket
	//Client连接代码end
	
	//Server发送/接收文件界面start
	JFrame jf5;
	JPanel jpl25;//jf5整体布局
    JPanel jpl26;//jpl25中的上方布局
    JPanel jpl27;//jpl26中的上方布局
    JPanel jpl28;//jpl26中的下方布局
    JPanel jpl29;//jpl25中的下方布局
    JButton jbt15;//客户端连接完成后的按键
    JButton jbt16;//发送文件
    Socket ServerSFile;//Server端的Socket
	//Server发送/接收文件界面end
	
	//Client发送/接收文件界面start
	JFrame jf6;
	JPanel jpl30;//jf4整体布局
    JPanel jpl31;//jpl30中的上方布局
    JPanel jpl32;//jpl31中的上方布局
    JPanel jpl33;//jpl31中的下方布局
    JTextField jtf3;//输入服务器IP
    JButton jbt17;//连接服务器
    JButton jbt18;//发送文件
    JPanel jpl34;//jpl30的下方布局
    Socket ClientSFile;//Server端的Socket
	//Client发送/接收文件界面end
	
	public class ServerThreadReceiveMessage implements Runnable//Server接收消息时的多线程内部类
	{
		public synchronized void run()
		{
			while(true)
			{
				try 
				{
					ServerReceiveString=jta1.getText()+"Client:"+ServerISM.readUTF()+"\n";
					jta1.setText(ServerReceiveString);
					repaint();
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	public class ServerThreadSendMessage implements Runnable//Server发送消息时的多线程内部类
	{
		public synchronized void run()
		{
			ServerSendString=jta2.getText();
			try 
			{
				ServerOSM.writeUTF(ServerSendString);
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ServerReceiveString=jta1.getText()+"Server:"+ServerSendString+"\n";
			jta1.setText(ServerReceiveString);
			jta2.setText("");
			repaint();
		}
	}
	public class ClientThreadReceiveMessage implements Runnable//Client接收消息时的多线程内部类
	{
		public synchronized void run()
		{
			while(true)
			{
				try 
				{
					ClientReceiveString=jta3.getText()+"Server:"+ClientISM.readUTF()+"\n";
					jta3.setText(ClientReceiveString);
					repaint();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public class ClientThreadSendMessage implements Runnable//Client发送消息时的多线程内部类
	{
		public synchronized void run()
		{
			ClientSendString=jta4.getText();
			try 
			{
				ClientOSM.writeUTF(ClientSendString);
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ClientReceiveString=jta3.getText()+"Client:"+ClientSendString+"\n";
			jta3.setText(ClientReceiveString);
			jta4.setText("");
			repaint();
		}
	}
	public class ServerThreadReceiveFile implements Runnable//Server接收文件时的多线程内部类
	{
		public synchronized void run()
		{
			while(true)
			{
				try
				{
					InputStream ServerFIS = ServerSFile.getInputStream();
					BufferedInputStream ServerBIS=new BufferedInputStream(ServerFIS);
					FileOutputStream ServerFOS=new FileOutputStream("D:\\ServerReceive.jpg");
					BufferedOutputStream ServerBOS=new BufferedOutputStream(ServerFOS);
					byte[] buf = new byte[1024];
					int len = 0;
					while((len=ServerBIS.read(buf))!=-1)
					{
						ServerBOS.write(buf,0,len);
					}
					ServerBOS.close();
					ServerBIS.close();
					JOptionPane.showMessageDialog(null,"接收文件完成，文件已成功保存在D:\\ServerReceive.jpg");
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public class ServerThreadSendFile implements Runnable//Server发送文件时的多线程内部类
	{
		public synchronized void run()
		{
			ServerJFC=new JFileChooser();
			ServerJFC.setDialogTitle("请选择待发送文件");
			int ChooseButton=ServerJFC.showOpenDialog(jf5);
			if(ChooseButton==JFileChooser.APPROVE_OPTION)
            {    
                   ServerF=ServerJFC.getSelectedFile();  
            }
			try 
			{
				OutputStream ServerFOS = ServerSFile.getOutputStream();
				BufferedOutputStream ServerBOS=new BufferedOutputStream(ServerFOS);
				FileInputStream ServerFIS=new FileInputStream(ServerF);
				BufferedInputStream ServerBIS=new BufferedInputStream(ServerFIS);
				byte[] buf = new byte[1024];
				int len = 0;
				while((len=ServerBIS.read(buf))!=-1)
				{
					ServerBOS.write(buf,0,len);
				}
				ServerBIS.close();
				ServerBOS.close();
				JOptionPane.showMessageDialog(null,"发送文件完成");
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public class ClientThreadReceiveFile implements Runnable//Client接收文件时的多线程内部类
	{
		public synchronized void run()
		{
			while(true)
			{
				try 
				{
					InputStream ClientFIS = ClientSFile.getInputStream();
					BufferedInputStream ClientBIS=new BufferedInputStream(ClientFIS);
					FileOutputStream ClientFOS=new FileOutputStream("D:\\ClientReceive.jpg");
					BufferedOutputStream ClientBOS=new BufferedOutputStream(ClientFOS);
					byte[] buf = new byte[1024];
					int len = 0;
					while((len=ClientBIS.read(buf))!=-1)
					{
						ClientBOS.write(buf,0,len);
					}
					ClientBOS.close();
					ClientBIS.close();
					JOptionPane.showMessageDialog(null,"接收文件完成，文件已成功保存在D:\\ClientReceive.jpg");
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public class ClientThreadSendFile implements Runnable//Client发送文件时的多线程内部类
	{
		public synchronized void run()
		{
			ClientJFC=new JFileChooser();
			ClientJFC.setDialogTitle("请选择待发送文件");
			int ChooseButton=ClientJFC.showOpenDialog(jf6);
			if(ChooseButton==JFileChooser.APPROVE_OPTION)
            {    
                   ClientF=ClientJFC.getSelectedFile();  
            }
			try 
			{
				OutputStream ClientFOS = ClientSFile.getOutputStream();
				BufferedOutputStream ClientBOS=new BufferedOutputStream(ClientFOS);
				FileInputStream ClientFIS=new FileInputStream(ClientF);
				BufferedInputStream ClientBIS=new BufferedInputStream(ClientFIS);
				byte[] buf = new byte[1024];
				int len = 0;
				while((len=ClientBIS.read(buf))!=-1)
				{
					ClientBOS.write(buf,0,len);
				}
				JOptionPane.showMessageDialog(null,"发送文件完成");
				ClientBIS.close();
				ClientBOS.close();
			} 
			catch (IOException e1)
			{
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		}
	}
	public class ServerThreadReceiveMessage_HaveLinked implements Runnable//Server发送消息连接后按键的多线程内部类
	{
		public void run()
		{
			try 
			{
				ServerS=ServerSS.accept();
				ServerISM=new DataInputStream(ServerS.getInputStream());
				ServerOSM=new DataOutputStream(ServerS.getOutputStream());
				stm=new Thread(new ServerThreadReceiveMessage());
				stm.start();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public class ServerThreadFile_HaveLinked implements Runnable//Server发送文件连接后按键的多线程内部类
	{
		public void run()
		{
			try 
			{
				ServerSFile=ServerSS.accept();
				InputStream ServerISF=ServerSFile.getInputStream();
				OutputStream ServerOSF=ServerSFile.getOutputStream();
				stf=new Thread(new ServerThreadReceiveFile());
				stf.start();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
    public MainProgram_2()
    {
    	
    	//登陆界面代码start
    	UserName[0]=new String("2012211706");
		UserPassword[0]=new String("2012211706");
		UserName[1]=new String("jxl");
		UserPassword[1]=new String("jxl");
		UserName[2]=new String("JXL");
		UserPassword[2]=new String("JXL");
		jf1=new JFrame();//登陆界面
		jf1.setLayout(new BorderLayout());
		jf2=new JFrame();//登陆成功界面
		jf2.setLayout(new BorderLayout());
		jpl1=new JPanel();//jf1中的整体布局
		jpl1.setLayout(new BorderLayout());
		jpl2=new JPanel();//jf1中的上方布局
		jpl2.setLayout(new FlowLayout());
		jpl3=new JPanel();//jf1中的中方布局
		jpl3.setLayout(new GridLayout(2,2,1,1));
		jpl3_temp=new JPanel();
		jpl3_temp.setLayout(new FlowLayout());
		jpl4=new JPanel();//jf1中的下方布局
		jpl4.setLayout(new FlowLayout());
		jpl5=new JPanel();//jf1中的下方布局
		jpl5.setLayout(new BorderLayout());
		//jf1的设置
		jbt1=new JButton("确认");
		jbt2=new JButton("重置");
		jbt3=new JButton("退出");
		jlb1=new JLabel("用户名：");
		jlb2=new JLabel("密码：");
		jlb3=new JLabel("请输入用户名和密码：");
		jtf1=new JTextField(10);
		jpf1=new JPasswordField(10);
		jpl2.add(jlb3);
		jpl3.add(jlb1);
		jpl3.add(jtf1);
		jpl3.add(jlb2);
		jpl3.add(jpf1);
		jpl3_temp.add(jpl3);
		jpl4.add(jbt1);
		jpl4.add(jbt2);
		jpl4.add(jbt3);
		jpl1.add(jpl2,BorderLayout.NORTH);
		jpl1.add(jpl3_temp,BorderLayout.CENTER);
		jpl1.add(jpl4,BorderLayout.SOUTH);
		jf1.add(jpl1,BorderLayout.CENTER);
		jbt1.addActionListener(this);//对确认按键的监听
		jbt2.addActionListener(this);//对重置按键的监听
		jbt3.addActionListener(this);//对退出按键的监听
		jtf1.addActionListener(this);//对用户名区域回车按键的监听
		jpf1.addActionListener(this);//对密码区域回车按键的监听
		jf1.setTitle("聊天软件(Made By JXL)");
		jf1.setSize(350,170);
		jf1.setLocationRelativeTo(null);
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setVisible(true);
		//登陆界面代码end
		
		//Server和Client选择界面代码start
		com[0]=new String("Server(服务器)");
		com[1]=new String("Client(客户端)");
		jcb1=new JComboBox(com);
		jpl5=new JPanel();
		jpl6=new JPanel();
		jpl7=new JPanel();
		jpl8=new JPanel();
		jpl6.add(new JLabel("请选择使用类别"));
		jpl7.add(jcb1);
		jbt4=new JButton("确认");
		jbt5=new JButton("退出");
		jpl8.add(jbt4);
		jpl8.add(jbt5);
		jpl5.setLayout(new BorderLayout());
		jpl5.add(jpl6,BorderLayout.NORTH);
		jpl5.add(jpl7,BorderLayout.CENTER);
		jpl5.add(jpl8,BorderLayout.SOUTH);
		jf2.add(jpl5);
		jbt4.addActionListener(this);//对确认键的监听
		jbt5.addActionListener(this);//对退出键的监听
		jf2.setTitle("聊天软件(Made By JXL)");
		jf2.setSize(350,170);
		jf2.setLocationRelativeTo(null);
		jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf2.setVisible(false);
		//Server和Client选择界面代码end
		
		//Server界面代码start
		try 
		{
			LocalIP=InetAddress.getLocalHost().getHostAddress();//获取本机IP地址
		} 
		catch (UnknownHostException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jf3=new JFrame();
		jpl9=new JPanel();
		jpl11=new JPanel();
		jpl13=new JPanel();
		jpl14=new JPanel();
		jpl17=new JPanel();
		jpl18=new JPanel();
		jpl19=new JPanel();
		jpl20=new JPanel();
		jpl11.setLayout(new BorderLayout());
	    jpl13.add(new JLabel("使用类别:Server(服务器)"));
	    jbt14=new JButton("客户端连接完成后请按此键");
	    jbt14.addActionListener(this);
	    jpl13.add(jbt14);
	    jpl14.add(new JLabel("本服务器IP:"+LocalIP));
	    jpl11.add(jpl13,BorderLayout.NORTH);
	    jpl11.add(jpl14,BorderLayout.SOUTH);
	    jta1=new JTextArea(20,50);
	    jta1.setEditable(false);//禁止编辑
	    jta1.setLineWrap (true);//开启自动换行
	    jta2=new JTextArea(10,50);
	    jta2.setLineWrap (true);//开启自动换行
	    jsp1=new JScrollPane(jta1);
	    jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //垂直滚动条
	    jsp1.setWheelScrollingEnabled(true);//支持滚轮滚动
	    jsp2=new JScrollPane(jta2);
	    jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //垂直滚动条
	    jsp2.setWheelScrollingEnabled(true);//支持滚轮滚动
	    jbt7=new JButton("开启多人群聊功能");
	    jbt8=new JButton("保存聊天记录到本地");
	    jbt9=new JButton("发送/接收文件");
	    jbt10=new JButton("发送信息");
	    jbt7.addActionListener(this);
	    jbt8.addActionListener(this);
	    jbt9.addActionListener(this);
	    jbt10.addActionListener(this);
	    jpl19.add(jsp1);
	    jpl20.add(jsp2);
	    jpl17.setLayout(new BorderLayout());
	    jpl17.add(jpl19,BorderLayout.NORTH);
	    jpl17.add(jpl20,BorderLayout.SOUTH);
	    jpl18.add(jbt7);
	    jpl18.add(jbt8);
	    jpl18.add(jbt9);
	    jpl18.add(jbt10);
	    jpl9.setLayout(new BorderLayout());
	    jpl9.add(jpl11,BorderLayout.NORTH);
	    jpl9.add(jpl17,BorderLayout.CENTER);
	    jpl9.add(jpl18,BorderLayout.SOUTH);
		jf3.add(jpl9);
		jf3.setTitle("聊天软件(Made By JXL)");
		jf3.setSize(1000,700);
		jf3.setLocationRelativeTo(null);
		jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf3.setVisible(false);
		//Server界面代码end
		
		//Client界面代码start
		jf4=new JFrame();
		jpl10=new JPanel();
		jpl12=new JPanel();
		jpl15=new JPanel();
		jpl16=new JPanel();
		jpl21=new JPanel();
		jpl22=new JPanel();
		jpl23=new JPanel();
		jpl24=new JPanel();
		jta3=new JTextArea(20,50);
		jta3.setEditable(false);//禁止编辑
		jta3.setLineWrap (true);//开启自动换行
	    jta4=new JTextArea(10,50);
	    jta4.setLineWrap (true);//开启自动换行
	    jsp3=new JScrollPane(jta3);
	    jsp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //垂直滚动条
	    jsp3.setWheelScrollingEnabled(true);//支持滚轮滚动
	    jsp4=new JScrollPane(jta4);
	    jsp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //垂直滚动条
	    jsp4.setWheelScrollingEnabled(true);//支持滚轮滚动
	    jbt11=new JButton("保存聊天记录到本地");
	    jbt12=new JButton("发送/接收文件");
	    jbt13=new JButton("发送信息");
	    jbt11.addActionListener(this);
	    jbt12.addActionListener(this);
	    jbt13.addActionListener(this);
		jpl12.setLayout(new BorderLayout());
	    jpl15.add(new JLabel("使用类别:Client(客户端)"));
	    jpl16.add(new JLabel("请输入服务器IP:"));
	    jtf2=new JTextField(20);
	    jbt6=new JButton("连接服务器");
	    jpl16.add(jtf2);
	    jpl16.add(jbt6);
	    jtf2.addActionListener(this);//对输入IP对话框中回车键的监听
	    jbt6.addActionListener(this);//对连接服务器按钮的监听
	    jpl12.add(jpl15,BorderLayout.NORTH);
	    jpl12.add(jpl16,BorderLayout.SOUTH);
	    jpl23.add(jsp3);
	    jpl24.add(jsp4);
	    jpl21.setLayout(new BorderLayout());
	    jpl21.add(jpl23,BorderLayout.NORTH);
	    jpl21.add(jpl24,BorderLayout.SOUTH);
	    jpl22.add(jbt11);
	    jpl22.add(jbt12);
	    jpl22.add(jbt13);
	    jpl10.setLayout(new BorderLayout());
	    jpl10.add(jpl12,BorderLayout.NORTH);
	    jpl10.add(jpl21,BorderLayout.CENTER);
	    jpl10.add(jpl22,BorderLayout.SOUTH);
		jf4.add(jpl10);
		jf4.setTitle("聊天软件(Made By JXL)");
		jf4.setSize(1000,700);
		jf4.setLocationRelativeTo(null);
		jf4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf4.setVisible(false);
		//Client界面代码end
		
		//Server发送/接收文件界面start
		jf5=new JFrame();
		jpl25=new JPanel();
		jpl26=new JPanel();
		jpl27=new JPanel();
		jpl28=new JPanel();
		jpl29=new JPanel();
		jpl27.add(new JLabel("使用类别:Server(服务器)"));
	    jbt15=new JButton("客户端连接完成后请按此键");
	    jbt15.addActionListener(this);
	    jpl27.add(jbt15);
	    jpl28.add(new JLabel("本服务器IP:"+LocalIP));
	    jpl26.setLayout(new BorderLayout());
	    jpl26.add(jpl27,BorderLayout.NORTH);
	    jpl26.add(jpl28,BorderLayout.SOUTH);
	    jbt16=new JButton("发送文件");
	    jbt16.addActionListener(this);
	    jpl29.add(jbt16);
	    jpl25.setLayout(new BorderLayout());
	    jpl25.add(jpl26,BorderLayout.NORTH);
	    jpl25.add(jpl29,BorderLayout.SOUTH);
	    jf5.add(jpl25);
	    jf5.setTitle("聊天软件(Made By JXL)");
		jf5.setSize(500,200);
		jf5.setLocationRelativeTo(null);
		//jf5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭此frame后程序结束
		jf5.setVisible(false);
		//Server发送/接收文件界面end
		
		//Client发送/接收文件界面start
		jf6=new JFrame();
		jpl30=new JPanel();
		jpl31=new JPanel();
		jpl32=new JPanel();
		jpl33=new JPanel();
		jpl34=new JPanel();
		jpl32.add(new JLabel("使用类别:Client(客户端)"));
	    jpl33.add(new JLabel("请输入服务器IP:"));
	    jtf3=new JTextField(20);
	    jbt17=new JButton("连接服务器");
	    jpl33.add(jtf3);
	    jpl33.add(jbt17);
	    jtf3.addActionListener(this);//对输入IP对话框中回车键的监听
	    jbt17.addActionListener(this);//对连接服务器按钮的监听
	    jpl31.setLayout(new BorderLayout());
	    jpl31.add(jpl32,BorderLayout.NORTH);
	    jpl31.add(jpl33,BorderLayout.SOUTH);
	    jbt18=new JButton("发送文件");
	    jbt18.addActionListener(this);
	    jpl34.add(jbt18);
	    jpl30.setLayout(new BorderLayout());
	    jpl30.add(jpl31,BorderLayout.NORTH);
	    jpl30.add(jpl34,BorderLayout.SOUTH);
	    jf6.add(jpl30);
	    jf6.setTitle("聊天软件(Made By JXL)");
		jf6.setSize(500,200);
		jf6.setLocationRelativeTo(null);
		//jf6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭此frame后程序结束
		jf6.setVisible(false);
		//Client发送/接收文件界面end
		
    }
    public void actionPerformed(ActionEvent e)
	{
    	
    	//登陆界面代码start
		if(e.getSource()==jbt1||e.getSource()==jtf1||e.getSource()==jpf1)//点击确认键或在用户名区域敲击回车键或在密码区域敲击回车键
		{
			String TempPassword=new String(jpf1.getPassword());
			for(int i=0;i<TotalOfUser;i++)
			{
				if(jtf1.getText().length()==0&&TempPassword.length()==0)//用户名和密码均为空
				{
					jlb3.setText("用户名和密码不能为空，请重新输入用户名和密码：");
					jtf1.setText("");
					jpf1.setText("");
					jf1.repaint();
					return;
				}
				else if(jtf1.getText().length()==0)//仅用户名为空
				{
					jlb3.setText("用户名不能为空，请重新输入用户名和密码：");
					jtf1.setText("");
					jpf1.setText("");
					jf1.repaint();
					return;
				}
				else if(TempPassword.length()==0)//仅密吗为空
				{
					jlb3.setText("密码不能为空，请重新输入用户名和密码：");
					jtf1.setText("");
					jpf1.setText("");
					jf1.repaint();
					return;
				}
				else if(UserName[i].equals(jtf1.getText())&&TempPassword.equals(UserPassword[i]))//用户名和密码输入正确
				{
					jf1.setVisible(false);
					jf2.setVisible(true);
					jf1.repaint();
					jf2.repaint();
					return;
				}
			}
			//用户名和密码输入错误
			jlb3.setText("你输入的用户名或密码有误，请重新输入用户名和密码：");
			jtf1.setText("");
			jpf1.setText("");
			jf1.repaint();
			return;
		}
		else if(e.getSource()==jbt2)//点击重置键
		{
			jtf1.setText("");
			jpf1.setText("");
			jf1.repaint();
		}
		else if(e.getSource()==jbt3)//点击退出键
		{
			System.exit(0);
		}
		//登陆界面代码end
		
		//Server和Client选择界面代码start
		if(e.getSource()==jbt4)//点击确认键
		{
			String comchoose=(String)jcb1.getSelectedItem();
			
			//Server界面代码start
			if(comchoose.equals(com[0]))//Server
			{
				jf2.setVisible(false);
				jf3.setVisible(true);
				try
				{
					ServerSS = new ServerSocket(9633);//端口号9633
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//Server界面代码end
			
			//Client界面代码start
			else if(comchoose.equals(com[1]))//Client
			{
				jf2.setVisible(false);
				jf4.setVisible(true);
			}
			//Client界面代码end
			
		}
		else if(e.getSource()==jbt5)//点击退出键
		{
			System.exit(0);
		}
		//Server和Client选择界面代码end
		
		//Server界面代码start
		if(e.getSource()==jbt7)//开启多人群聊功能
		{
			JOptionPane.showMessageDialog(null,"此功能正在开发中，还未完成！");
		}
		if(e.getSource()==jbt8)//保存聊天记录到本地
		{
			try 
			{
				FileOutputStream fos = new FileOutputStream("D:\\TalkingMessage.txt");
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos=new DataOutputStream(bos);
				ServerReceiveString=jta1.getText();
				dos.writeChars(ServerReceiveString);
				dos.close();
				JOptionPane.showMessageDialog(null,"聊天记录已成功保存在D:\\TalkingMessage.txt");
			}
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==jbt9)//发送/接收文件
		{
			jf5.setVisible(true);
		}
		if(e.getSource()==jbt10)//发送信息
		{
			Thread serverthreadsendmessage=new Thread(new ServerThreadSendMessage());
			serverthreadsendmessage.start();
		}
		if(e.getSource()==jbt14)//Client连接完成后的按键
		{
			Thread serverthreadmessage_havelinked=new Thread(new ServerThreadReceiveMessage_HaveLinked());
			serverthreadmessage_havelinked.start();
		}
		//Server界面代码end
		
		//Client界面代码start
		if(e.getSource()==jbt6||e.getSource()==jtf2)//输入IP框内键入回车或点击连接键
		{
			try 
			{
				ClientS=new Socket(jtf2.getText(),9633);
				ClientISM=new DataInputStream(ClientS.getInputStream());
				ClientOSM=new DataOutputStream(ClientS.getOutputStream());
				ctm=new Thread(new ClientThreadReceiveMessage());
				ctm.start();
			} 
			catch (UnknownHostException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==jbt11)//保存聊天记录到本地
		{
			try 
			{
				FileOutputStream fos = new FileOutputStream("D:\\TalkingMessage.txt");
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos=new DataOutputStream(bos);
				ClientReceiveString=jta3.getText();
				dos.writeChars(ClientReceiveString);
				dos.close();
				JOptionPane.showMessageDialog(null,"聊天记录已成功保存在D:\\TalkingMessage.txt");
			}
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		if(e.getSource()==jbt12)//发送/接收文件
		{
			jf6.setVisible(true);
		}
		if(e.getSource()==jbt13)//发送信息
		{
			Thread clientthreadsendmessage=new Thread(new ClientThreadSendMessage());
			clientthreadsendmessage.start();
		}
		//Client界面代码end
		
		//Server发送/接收文件界面start
		if(e.getSource()==jbt15)//Client连接完成后的按键
		{
			Thread serverthreadfile_havelinked=new Thread(new ServerThreadFile_HaveLinked());
			serverthreadfile_havelinked.start();			
		}
		if(e.getSource()==jbt16)//发送文件
		{
			Thread serverthreadsendfile=new Thread(new ServerThreadSendFile());
			serverthreadsendfile.start();
		}
		//Server发送/接收文件界面end
		
		//Client发送/接收文件界面start
		if(e.getSource()==jtf3||e.getSource()==jbt17)//输入IP框内键入回车或点击连接键
		{
			try 
			{
				ClientSFile=new Socket(jtf3.getText(),9633);
				InputStream ClientISF=ClientSFile.getInputStream();
				OutputStream ClientOSF=ClientSFile.getOutputStream();
				ctf=new Thread(new ClientThreadReceiveFile());
				ctf.start();
			}
			catch (UnknownHostException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==jbt18)//发送文件
		{
			Thread clientthreadsendfile=new Thread(new ClientThreadSendFile());
			clientthreadsendfile.start();
		}
		//Client发送/接收文件界面end
		
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new MainProgram_2();
	}
}
