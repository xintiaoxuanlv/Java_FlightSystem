package _interface;
//���ܣ��û���Ʊʱ��Ľ��棬��Ҫ����ʾ������Ϣ����ʾ�û�������Ϣ�������û���Ʊ��Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _user.Book_tickets;
import _user.ShowTicketsMessageInText;
import _flight.List;
import _manager.ModifyFlight;
import _manager.DataCheck;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Book_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;//���к�
	private static String userName;//��Ŵ��������û���
	private JTextArea text1 = new JTextArea(10,63);
	private JTextArea text2 = new JTextArea(10,40);
	private JScrollPane textroll1 = new JScrollPane(text1);
	private JScrollPane textroll2 = new JScrollPane(text2);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JLabel jl1 = new JLabel("�����:");
	private JLabel jl2 = new JLabel("��Ʊ��:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("�ҵ�Ǯ��");
	private JButton jb2 = new JButton("����");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");//��ܵ����Ͻ�ͼ��
	public Book_interface(String _name){//����Ϊ��¼ʱ���û���
		super("��Ʊ");
		super.setIconImage(iocn);//��ʾͼ��
		userName = _name;//���û���������
		//����������
		showlist();
		setSize(800,600);
		setLayout(new GridLayout(3,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
		//���Ӳ��ֿؼ�
		add(jp1);add(jp2);add(jp3);
		jp1.add(textroll1);jp2.add(textroll2);
		//���õ�һ��������������
		textroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll1.setBounds(20,20,100,200);
		text1.setLineWrap(true); 
		text1.setEditable(false); 
		//���õڶ���������������
		textroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll2.setBounds(20,20,100,200);
		text2.setLineWrap(true); 
		text2.setEditable(false); 
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg3.jpg");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		//�������ؼ�����Ϊ͸��
		text1.setOpaque(false);
		text2.setOpaque(false);
		textroll1.setOpaque(false);
		textroll2.setOpaque(false);
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jl1.setOpaque(false);
		jl2.setOpaque(false);
		jt1.setOpaque(false);
		jt2.setOpaque(false);
		//���ð�ť��С
		jb2.setPreferredSize(new Dimension(250, 50));
		jb1.setPreferredSize(new Dimension(250, 50));
		//���ð�ť��ɫ�Ͱ�ť����������
		jb2.setBackground(new Color(155,155,155));
		jb2.setFont(new Font("��Բ",Font.BOLD,15));
		jb1.setBackground(new Color(155,255,155));
		jb1.setFont(new Font("��Բ",Font.BOLD,15));
		//���ñ�ǩ�ַ�
		jl1.setFont(new Font("��Բ",Font.BOLD,20));
		jl2.setFont(new Font("��Բ",Font.BOLD,20));
		//���ӿؼ�
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp4);jp3.add(jp5);
		jp4.add(jl1);jp4.add(jt1);jp4.add(jl2);jp4.add(jt2);
		jp5.add(jb1);
		jp5.add(jb2);
		//���Ӽ�����
		jb1.addActionListener(this);jb2.addActionListener(this);
		//���ı����������û���Ϣ
		text2.setText("�𾴵��û�"+userName+":\r\n");
		text2.append(ShowTicketsMessageInText.showMessage(userName)+"\r\n");
	}
	public void actionPerformed(ActionEvent e){
		//�ҵ�Ǯ������
		if(e.getSource()==jb1){
			ChangeMoneyInterface temp = new ChangeMoneyInterface(userName);
			temp.setEnabled(true);
		}
		//��Ʊ����
		else{
			//��������Ƿ���Ϲ淶
			boolean jt1Check,jt2Check;
			jt1Check = DataCheck.check(jt1.getText());
			jt2Check = DataCheck.check(jt2.getText());
			if(!(jt1Check&&jt2Check)){
				text2.append("������������ʽ����ȷ\r\n");
				return;
			}
			//���ù�Ʊ���������ع�Ʊ״̬��0Ϊ��Ʊ�ɹ���1Ϊ���������2ΪƱ�����㣬3��Ǯ����
			int isBook = Book_tickets.book(jt1.getText(),jt2.getText(),userName);
			//�������ڸ�ʽ
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// new Date()Ϊ��ȡ��ǰϵͳʱ�䣬���������û�������Ϣ��
			text2.append(df.format(new Date()));
			if(isBook==0)
				text2.append("����Ʊ�ɹ�����\r\n");
			else if(isBook == 1)
				text2.append("��������������󣡣�\r\n");
			else if(isBook == 2)
				text2.append("��"+jt1.getText()+"�ź���ʣ��Ʊ������"+jt2.getText()+"��!!\r\n");
			else if(isBook == 3)
				text2.append("������ʣ�����!!\r\n");
			showlist();
			// new Date()Ϊ��ȡ��ǰϵͳʱ��
			text2.append(df.format(new Date()));
			text2.append("\r\n"+ShowTicketsMessageInText.showMessage(userName)+"\r\n");
		}
	}
	//��ʾ������Ϣ
	public void showlist(){
		text1.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\tƱ��\t���ʱ��\t����ʱ��\r\n");
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[8];
			List.list(i, temp);
			text1.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"Сʱ\r\n");
		}
	}
}