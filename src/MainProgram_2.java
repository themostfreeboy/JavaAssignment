//2014.6.22.2�棬2014��6��22�յ�2��
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
	
	//��½�������start
	JFrame jf1;//��½����
	JPanel jpl1;//jf1�е����岼��
	JPanel jpl2;//jf1�е��Ϸ�����
	JPanel jpl3;//jf1�е��з�����
	JPanel jpl3_temp;//jpl3�е���ʱ���֣����ڽ��jtf1��jpf1�е�text�������޷Ŵ������
	JPanel jpl4;//jf1�е��·�����
	JButton jbt1;//jf1�е�ȷ��
	JButton jbt2;//jf1�е�����
	JButton jbt3;//jf1�е��˳�
	JLabel jlb1;//�û���
	JLabel jlb2;//����
	JLabel jlb3;//��ʾ�����û���������
	JTextField jtf1;//�û���
	JPasswordField jpf1;//����
	String[] UserName=new String[100];
	String[] UserPassword=new String[100];
    int TotalOfUser=3;
	//��½�������end
    
    //Server��Clientѡ��������start
    JFrame jf2;//Server��Client��ѡ�����
    JPanel jpl5;//jf2�е����岼��
    JPanel jpl6;//jf2�е��Ϸ�����
    JPanel jpl7;//jf2�е��м䲼��
    JPanel jpl8;//jf2�е��·�����
    JComboBox jcb1;//Server��Client��ѡ��
    String[] com=new String[2];//ѡ���б���Ŀ����
    JButton jbt4;//jf2�е�ȷ��
    JButton jbt5;//jf2�е��˳�
    //Server��Clientѡ��������end
    
    //Server�������start
    String LocalIP;//��¼����IP
    JFrame jf3;//Server����
    JPanel jpl9;//jf3���岼��
    JPanel jpl11;//jpl9�е��Ϸ�����
    JPanel jpl13;//jpl11�е��Ϸ�����
    JPanel jpl14;//jpl11�е��·�����
    JPanel jpl17;//jpl9���м䲼��
    JPanel jpl18;//jpl9���·�����
    JPanel jpl19;//jpl17���Ϸ�����
    JPanel jpl20;//jpl17���·�����
    JButton jbt7;//��������Ⱥ�Ĺ���
    JButton jbt8;//���������¼������
    JButton jbt9;//����/�����ļ�
    JButton jbt10;//������Ϣ
    JTextArea jta1;//�ѽ��յ���Ϣ
    JTextArea jta2;//�����͵���Ϣ
    JScrollPane jsp1;//�ѽ�����Ϣ�Ĺ�����
	JScrollPane jsp2;//��������Ϣ�Ĺ�����
	JButton jbt14;//�ͻ���������ɺ�İ���
	static String ServerSendString="";//Server���͵��ַ���
	static String ServerReceiveString="";//Server���յ��ַ���
	DataInputStream ServerISM;//Server��ȡ��Ϣ������
	DataOutputStream ServerOSM;//Serverд����Ϣ������
	JFileChooser ServerJFC;//Server�����ļ�����
	File ServerF;//Server���͵��ļ�
	Thread stm;//Server������Ϣ�߳�
	Thread stf;//Server�����ļ��߳�
  	//Server�������end
  		
  	//Client�������start
    JFrame jf4;//Client����
    JPanel jpl10;//jf4���岼��
    JPanel jpl12;//jpl10�е��Ϸ�����
    JPanel jpl15;//jpl12�е��Ϸ�����
    JPanel jpl16;//jpl12�е��·�����
    JTextField jtf2;//���������IP
    JButton jbt6;//���ӷ�����
    JPanel jpl21;//jpl10���м䲼��
    JPanel jpl22;//jpl10���·�����
    JPanel jpl23;//jpl21���Ϸ�����
    JPanel jpl24;//jpl21���·�����
    JButton jbt11;//���������¼������
    JButton jbt12;//����/�����ļ�
    JButton jbt13;//������Ϣ
    JTextArea jta3;//�ѽ��յ���Ϣ
    JTextArea jta4;//�����͵���Ϣ
    JScrollPane jsp3;//�ѽ�����Ϣ�Ĺ�����
	JScrollPane jsp4;//��������Ϣ�Ĺ�����
	static String ClientSendString="";//Client���͵��ַ���
	static String ClientReceiveString="";//Client���յ��ַ���
	DataInputStream ClientISM;//Client��ȡ��Ϣ������
	DataOutputStream ClientOSM;//Clientд����Ϣ������
	JFileChooser ClientJFC;//Client�����ļ�����
	File ClientF;//Client���͵��ļ�
	Thread ctm;//Client������Ϣ�߳�
	Thread ctf;//Client�����ļ��߳�
  	//Client�������end
	
	//Server���Ӵ���start
	ServerSocket ServerSS;//Server�˵�Socket����
	Socket ServerS;//Server�˵�Socket
	//Server���Ӵ���end
	
    //Client���Ӵ���start
	Socket ClientS;//Client�� ��Socket
	//Client���Ӵ���end
	
	//Server����/�����ļ�����start
	JFrame jf5;
	JPanel jpl25;//jf5���岼��
    JPanel jpl26;//jpl25�е��Ϸ�����
    JPanel jpl27;//jpl26�е��Ϸ�����
    JPanel jpl28;//jpl26�е��·�����
    JPanel jpl29;//jpl25�е��·�����
    JButton jbt15;//�ͻ���������ɺ�İ���
    JButton jbt16;//�����ļ�
    Socket ServerSFile;//Server�˵�Socket
	//Server����/�����ļ�����end
	
	//Client����/�����ļ�����start
	JFrame jf6;
	JPanel jpl30;//jf4���岼��
    JPanel jpl31;//jpl30�е��Ϸ�����
    JPanel jpl32;//jpl31�е��Ϸ�����
    JPanel jpl33;//jpl31�е��·�����
    JTextField jtf3;//���������IP
    JButton jbt17;//���ӷ�����
    JButton jbt18;//�����ļ�
    JPanel jpl34;//jpl30���·�����
    Socket ClientSFile;//Server�˵�Socket
	//Client����/�����ļ�����end
	
	public class ServerThreadReceiveMessage implements Runnable//Server������Ϣʱ�Ķ��߳��ڲ���
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
	public class ServerThreadSendMessage implements Runnable//Server������Ϣʱ�Ķ��߳��ڲ���
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
	public class ClientThreadReceiveMessage implements Runnable//Client������Ϣʱ�Ķ��߳��ڲ���
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
	public class ClientThreadSendMessage implements Runnable//Client������Ϣʱ�Ķ��߳��ڲ���
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
	public class ServerThreadReceiveFile implements Runnable//Server�����ļ�ʱ�Ķ��߳��ڲ���
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
					JOptionPane.showMessageDialog(null,"�����ļ���ɣ��ļ��ѳɹ�������D:\\ServerReceive.jpg");
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public class ServerThreadSendFile implements Runnable//Server�����ļ�ʱ�Ķ��߳��ڲ���
	{
		public synchronized void run()
		{
			ServerJFC=new JFileChooser();
			ServerJFC.setDialogTitle("��ѡ��������ļ�");
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
				JOptionPane.showMessageDialog(null,"�����ļ����");
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public class ClientThreadReceiveFile implements Runnable//Client�����ļ�ʱ�Ķ��߳��ڲ���
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
					JOptionPane.showMessageDialog(null,"�����ļ���ɣ��ļ��ѳɹ�������D:\\ClientReceive.jpg");
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public class ClientThreadSendFile implements Runnable//Client�����ļ�ʱ�Ķ��߳��ڲ���
	{
		public synchronized void run()
		{
			ClientJFC=new JFileChooser();
			ClientJFC.setDialogTitle("��ѡ��������ļ�");
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
				JOptionPane.showMessageDialog(null,"�����ļ����");
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
	public class ServerThreadReceiveMessage_HaveLinked implements Runnable//Server������Ϣ���Ӻ󰴼��Ķ��߳��ڲ���
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
	public class ServerThreadFile_HaveLinked implements Runnable//Server�����ļ����Ӻ󰴼��Ķ��߳��ڲ���
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
    	
    	//��½�������start
    	UserName[0]=new String("2012211706");
		UserPassword[0]=new String("2012211706");
		UserName[1]=new String("jxl");
		UserPassword[1]=new String("jxl");
		UserName[2]=new String("JXL");
		UserPassword[2]=new String("JXL");
		jf1=new JFrame();//��½����
		jf1.setLayout(new BorderLayout());
		jf2=new JFrame();//��½�ɹ�����
		jf2.setLayout(new BorderLayout());
		jpl1=new JPanel();//jf1�е����岼��
		jpl1.setLayout(new BorderLayout());
		jpl2=new JPanel();//jf1�е��Ϸ�����
		jpl2.setLayout(new FlowLayout());
		jpl3=new JPanel();//jf1�е��з�����
		jpl3.setLayout(new GridLayout(2,2,1,1));
		jpl3_temp=new JPanel();
		jpl3_temp.setLayout(new FlowLayout());
		jpl4=new JPanel();//jf1�е��·�����
		jpl4.setLayout(new FlowLayout());
		jpl5=new JPanel();//jf1�е��·�����
		jpl5.setLayout(new BorderLayout());
		//jf1������
		jbt1=new JButton("ȷ��");
		jbt2=new JButton("����");
		jbt3=new JButton("�˳�");
		jlb1=new JLabel("�û�����");
		jlb2=new JLabel("���룺");
		jlb3=new JLabel("�������û��������룺");
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
		jbt1.addActionListener(this);//��ȷ�ϰ����ļ���
		jbt2.addActionListener(this);//�����ð����ļ���
		jbt3.addActionListener(this);//���˳������ļ���
		jtf1.addActionListener(this);//���û�������س������ļ���
		jpf1.addActionListener(this);//����������س������ļ���
		jf1.setTitle("�������(Made By JXL)");
		jf1.setSize(350,170);
		jf1.setLocationRelativeTo(null);
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setVisible(true);
		//��½�������end
		
		//Server��Clientѡ��������start
		com[0]=new String("Server(������)");
		com[1]=new String("Client(�ͻ���)");
		jcb1=new JComboBox(com);
		jpl5=new JPanel();
		jpl6=new JPanel();
		jpl7=new JPanel();
		jpl8=new JPanel();
		jpl6.add(new JLabel("��ѡ��ʹ�����"));
		jpl7.add(jcb1);
		jbt4=new JButton("ȷ��");
		jbt5=new JButton("�˳�");
		jpl8.add(jbt4);
		jpl8.add(jbt5);
		jpl5.setLayout(new BorderLayout());
		jpl5.add(jpl6,BorderLayout.NORTH);
		jpl5.add(jpl7,BorderLayout.CENTER);
		jpl5.add(jpl8,BorderLayout.SOUTH);
		jf2.add(jpl5);
		jbt4.addActionListener(this);//��ȷ�ϼ��ļ���
		jbt5.addActionListener(this);//���˳����ļ���
		jf2.setTitle("�������(Made By JXL)");
		jf2.setSize(350,170);
		jf2.setLocationRelativeTo(null);
		jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf2.setVisible(false);
		//Server��Clientѡ��������end
		
		//Server�������start
		try 
		{
			LocalIP=InetAddress.getLocalHost().getHostAddress();//��ȡ����IP��ַ
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
	    jpl13.add(new JLabel("ʹ�����:Server(������)"));
	    jbt14=new JButton("�ͻ���������ɺ��밴�˼�");
	    jbt14.addActionListener(this);
	    jpl13.add(jbt14);
	    jpl14.add(new JLabel("��������IP:"+LocalIP));
	    jpl11.add(jpl13,BorderLayout.NORTH);
	    jpl11.add(jpl14,BorderLayout.SOUTH);
	    jta1=new JTextArea(20,50);
	    jta1.setEditable(false);//��ֹ�༭
	    jta1.setLineWrap (true);//�����Զ�����
	    jta2=new JTextArea(10,50);
	    jta2.setLineWrap (true);//�����Զ�����
	    jsp1=new JScrollPane(jta1);
	    jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //��ֱ������
	    jsp1.setWheelScrollingEnabled(true);//֧�ֹ��ֹ���
	    jsp2=new JScrollPane(jta2);
	    jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //��ֱ������
	    jsp2.setWheelScrollingEnabled(true);//֧�ֹ��ֹ���
	    jbt7=new JButton("��������Ⱥ�Ĺ���");
	    jbt8=new JButton("���������¼������");
	    jbt9=new JButton("����/�����ļ�");
	    jbt10=new JButton("������Ϣ");
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
		jf3.setTitle("�������(Made By JXL)");
		jf3.setSize(1000,700);
		jf3.setLocationRelativeTo(null);
		jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf3.setVisible(false);
		//Server�������end
		
		//Client�������start
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
		jta3.setEditable(false);//��ֹ�༭
		jta3.setLineWrap (true);//�����Զ�����
	    jta4=new JTextArea(10,50);
	    jta4.setLineWrap (true);//�����Զ�����
	    jsp3=new JScrollPane(jta3);
	    jsp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //��ֱ������
	    jsp3.setWheelScrollingEnabled(true);//֧�ֹ��ֹ���
	    jsp4=new JScrollPane(jta4);
	    jsp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //��ֱ������
	    jsp4.setWheelScrollingEnabled(true);//֧�ֹ��ֹ���
	    jbt11=new JButton("���������¼������");
	    jbt12=new JButton("����/�����ļ�");
	    jbt13=new JButton("������Ϣ");
	    jbt11.addActionListener(this);
	    jbt12.addActionListener(this);
	    jbt13.addActionListener(this);
		jpl12.setLayout(new BorderLayout());
	    jpl15.add(new JLabel("ʹ�����:Client(�ͻ���)"));
	    jpl16.add(new JLabel("�����������IP:"));
	    jtf2=new JTextField(20);
	    jbt6=new JButton("���ӷ�����");
	    jpl16.add(jtf2);
	    jpl16.add(jbt6);
	    jtf2.addActionListener(this);//������IP�Ի����лس����ļ���
	    jbt6.addActionListener(this);//�����ӷ�������ť�ļ���
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
		jf4.setTitle("�������(Made By JXL)");
		jf4.setSize(1000,700);
		jf4.setLocationRelativeTo(null);
		jf4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf4.setVisible(false);
		//Client�������end
		
		//Server����/�����ļ�����start
		jf5=new JFrame();
		jpl25=new JPanel();
		jpl26=new JPanel();
		jpl27=new JPanel();
		jpl28=new JPanel();
		jpl29=new JPanel();
		jpl27.add(new JLabel("ʹ�����:Server(������)"));
	    jbt15=new JButton("�ͻ���������ɺ��밴�˼�");
	    jbt15.addActionListener(this);
	    jpl27.add(jbt15);
	    jpl28.add(new JLabel("��������IP:"+LocalIP));
	    jpl26.setLayout(new BorderLayout());
	    jpl26.add(jpl27,BorderLayout.NORTH);
	    jpl26.add(jpl28,BorderLayout.SOUTH);
	    jbt16=new JButton("�����ļ�");
	    jbt16.addActionListener(this);
	    jpl29.add(jbt16);
	    jpl25.setLayout(new BorderLayout());
	    jpl25.add(jpl26,BorderLayout.NORTH);
	    jpl25.add(jpl29,BorderLayout.SOUTH);
	    jf5.add(jpl25);
	    jf5.setTitle("�������(Made By JXL)");
		jf5.setSize(500,200);
		jf5.setLocationRelativeTo(null);
		//jf5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ�frame��������
		jf5.setVisible(false);
		//Server����/�����ļ�����end
		
		//Client����/�����ļ�����start
		jf6=new JFrame();
		jpl30=new JPanel();
		jpl31=new JPanel();
		jpl32=new JPanel();
		jpl33=new JPanel();
		jpl34=new JPanel();
		jpl32.add(new JLabel("ʹ�����:Client(�ͻ���)"));
	    jpl33.add(new JLabel("�����������IP:"));
	    jtf3=new JTextField(20);
	    jbt17=new JButton("���ӷ�����");
	    jpl33.add(jtf3);
	    jpl33.add(jbt17);
	    jtf3.addActionListener(this);//������IP�Ի����лس����ļ���
	    jbt17.addActionListener(this);//�����ӷ�������ť�ļ���
	    jpl31.setLayout(new BorderLayout());
	    jpl31.add(jpl32,BorderLayout.NORTH);
	    jpl31.add(jpl33,BorderLayout.SOUTH);
	    jbt18=new JButton("�����ļ�");
	    jbt18.addActionListener(this);
	    jpl34.add(jbt18);
	    jpl30.setLayout(new BorderLayout());
	    jpl30.add(jpl31,BorderLayout.NORTH);
	    jpl30.add(jpl34,BorderLayout.SOUTH);
	    jf6.add(jpl30);
	    jf6.setTitle("�������(Made By JXL)");
		jf6.setSize(500,200);
		jf6.setLocationRelativeTo(null);
		//jf6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ�frame��������
		jf6.setVisible(false);
		//Client����/�����ļ�����end
		
    }
    public void actionPerformed(ActionEvent e)
	{
    	
    	//��½�������start
		if(e.getSource()==jbt1||e.getSource()==jtf1||e.getSource()==jpf1)//���ȷ�ϼ������û��������û��س����������������û��س���
		{
			String TempPassword=new String(jpf1.getPassword());
			for(int i=0;i<TotalOfUser;i++)
			{
				if(jtf1.getText().length()==0&&TempPassword.length()==0)//�û����������Ϊ��
				{
					jlb3.setText("�û��������벻��Ϊ�գ������������û��������룺");
					jtf1.setText("");
					jpf1.setText("");
					jf1.repaint();
					return;
				}
				else if(jtf1.getText().length()==0)//���û���Ϊ��
				{
					jlb3.setText("�û�������Ϊ�գ������������û��������룺");
					jtf1.setText("");
					jpf1.setText("");
					jf1.repaint();
					return;
				}
				else if(TempPassword.length()==0)//������Ϊ��
				{
					jlb3.setText("���벻��Ϊ�գ������������û��������룺");
					jtf1.setText("");
					jpf1.setText("");
					jf1.repaint();
					return;
				}
				else if(UserName[i].equals(jtf1.getText())&&TempPassword.equals(UserPassword[i]))//�û���������������ȷ
				{
					jf1.setVisible(false);
					jf2.setVisible(true);
					jf1.repaint();
					jf2.repaint();
					return;
				}
			}
			//�û����������������
			jlb3.setText("��������û������������������������û��������룺");
			jtf1.setText("");
			jpf1.setText("");
			jf1.repaint();
			return;
		}
		else if(e.getSource()==jbt2)//������ü�
		{
			jtf1.setText("");
			jpf1.setText("");
			jf1.repaint();
		}
		else if(e.getSource()==jbt3)//����˳���
		{
			System.exit(0);
		}
		//��½�������end
		
		//Server��Clientѡ��������start
		if(e.getSource()==jbt4)//���ȷ�ϼ�
		{
			String comchoose=(String)jcb1.getSelectedItem();
			
			//Server�������start
			if(comchoose.equals(com[0]))//Server
			{
				jf2.setVisible(false);
				jf3.setVisible(true);
				try
				{
					ServerSS = new ServerSocket(9633);//�˿ں�9633
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//Server�������end
			
			//Client�������start
			else if(comchoose.equals(com[1]))//Client
			{
				jf2.setVisible(false);
				jf4.setVisible(true);
			}
			//Client�������end
			
		}
		else if(e.getSource()==jbt5)//����˳���
		{
			System.exit(0);
		}
		//Server��Clientѡ��������end
		
		//Server�������start
		if(e.getSource()==jbt7)//��������Ⱥ�Ĺ���
		{
			JOptionPane.showMessageDialog(null,"�˹������ڿ����У���δ��ɣ�");
		}
		if(e.getSource()==jbt8)//���������¼������
		{
			try 
			{
				FileOutputStream fos = new FileOutputStream("D:\\TalkingMessage.txt");
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos=new DataOutputStream(bos);
				ServerReceiveString=jta1.getText();
				dos.writeChars(ServerReceiveString);
				dos.close();
				JOptionPane.showMessageDialog(null,"�����¼�ѳɹ�������D:\\TalkingMessage.txt");
			}
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==jbt9)//����/�����ļ�
		{
			jf5.setVisible(true);
		}
		if(e.getSource()==jbt10)//������Ϣ
		{
			Thread serverthreadsendmessage=new Thread(new ServerThreadSendMessage());
			serverthreadsendmessage.start();
		}
		if(e.getSource()==jbt14)//Client������ɺ�İ���
		{
			Thread serverthreadmessage_havelinked=new Thread(new ServerThreadReceiveMessage_HaveLinked());
			serverthreadmessage_havelinked.start();
		}
		//Server�������end
		
		//Client�������start
		if(e.getSource()==jbt6||e.getSource()==jtf2)//����IP���ڼ���س��������Ӽ�
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
		if(e.getSource()==jbt11)//���������¼������
		{
			try 
			{
				FileOutputStream fos = new FileOutputStream("D:\\TalkingMessage.txt");
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos=new DataOutputStream(bos);
				ClientReceiveString=jta3.getText();
				dos.writeChars(ClientReceiveString);
				dos.close();
				JOptionPane.showMessageDialog(null,"�����¼�ѳɹ�������D:\\TalkingMessage.txt");
			}
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		if(e.getSource()==jbt12)//����/�����ļ�
		{
			jf6.setVisible(true);
		}
		if(e.getSource()==jbt13)//������Ϣ
		{
			Thread clientthreadsendmessage=new Thread(new ClientThreadSendMessage());
			clientthreadsendmessage.start();
		}
		//Client�������end
		
		//Server����/�����ļ�����start
		if(e.getSource()==jbt15)//Client������ɺ�İ���
		{
			Thread serverthreadfile_havelinked=new Thread(new ServerThreadFile_HaveLinked());
			serverthreadfile_havelinked.start();			
		}
		if(e.getSource()==jbt16)//�����ļ�
		{
			Thread serverthreadsendfile=new Thread(new ServerThreadSendFile());
			serverthreadsendfile.start();
		}
		//Server����/�����ļ�����end
		
		//Client����/�����ļ�����start
		if(e.getSource()==jtf3||e.getSource()==jbt17)//����IP���ڼ���س��������Ӽ�
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
		if(e.getSource()==jbt18)//�����ļ�
		{
			Thread clientthreadsendfile=new Thread(new ClientThreadSendFile());
			clientthreadsendfile.start();
		}
		//Client����/�����ļ�����end
		
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new MainProgram_2();
	}
}
