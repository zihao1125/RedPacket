//用户类
public class User {
    private String ID;
    private String name;
    private double balance;

    //构造方法
    public User(){
        this("unknow","unknow",0.0);
    }

    //构造方法
    public User(String ID,String name,double balance){
        this.ID=ID;
        this.name=name;
        this.balance=balance;
    }

    //setter
    public void setID(String ID){this.ID=ID;}
    public void setName(String name){this.name=name;}
    public void setBalance(double balance){this.balance=balance;}

    //getter
    public String getID(){return this.ID;}
    public String getName(){return this.name;}
    public double getBalance(){return this.balance;}

    //发红包
    public boolean send(double money){
        if(money > 0 && this.balance >= money){
            this.balance -= money;
            return true;
        }else{
            return false;
        }
    }

    //收红包
    public void receive(double money){
        this.balance += money;
    }

    //查看金额
    public void printBalance(){
        System.out.println(this.name+"的余额: "+this.balance);
    }
}

//红包类
class RedPacket{
    private User sender;
    private User receiver;
    private double amount;
    private String greeting;
    private boolean isReceived;
    private boolean isSent;

    //构造方法
    public RedPacket(){
        this(new User(), new User(), 0.0, "恭喜发财，大吉大利");
    }
    //构造方法
    public RedPacket(User sender,User receiver,double amount,String greeting){
        this.sender=sender;
        this.receiver=receiver;
        this.amount=amount;
        this.greeting=greeting;
        this.isReceived=false;
        this.isSent=false;
    }

    //setter
    public void setSender(User sender){this.sender=sender;}
    public void setReceiver(User receiver){this.receiver=receiver;}
    public void setAmount(double amount){this.amount=amount;}
    public void setGreeting(String greeting){this.greeting=greeting;}

    //getter
    public User getSender(){return this.sender;}
    public User getReceiver(){return this.receiver;}
    public double getAmount(){return this.amount;}
    public String getGreeting(){return this.greeting;}
    public boolean getIsReceived(){return this.isReceived;}
    public boolean getIsSent(){return this.isSent;}

    //发送红包
    public boolean sendPacket() {
        if (isSent) {
            System.out.println("红包已经发送过了！");
            return false;
        }
        if (sender.send(amount)) {
            isSent = true;
            System.out.println(sender.getName() + " 发送了 " + amount + " 元红包给 " + receiver.getName());
            System.out.println("祝福语：" + greeting);
            return true;
        } else {
            System.out.println("发送失败，余额不足！");
            return false;
        }
    }

    //接收红包
    public boolean receivePacket() {
        if (!isSent) {
            System.out.println("红包还未发送，无法接收！");
            return false;
        }
        if (isReceived) {
            System.out.println("红包已经被接收过了！");
            return false;
        }
        receiver.receive(amount);
        isReceived = true;
        System.out.println(receiver.getName() + " 成功接收了 " + sender.getName() + " 的 " + amount + " 元红包");
        return true;
    }

    //查看红包状态
    public void printStatus() {
        System.out.println("红包详情：");
        System.out.println("发送者：" + sender.getName());
        System.out.println("接收者：" + receiver.getName());
        System.out.println("金额：" + amount + " 元");
        System.out.println("祝福语：" + greeting);
        System.out.println("发送状态：" + (isSent ? "已发送" : "未发送"));
        System.out.println("接收状态：" + (isReceived ? "已接收" : "未接收"));
    }
}

//演示类
class RedPacketDemo {
    public static void main(String[] args) {
        //创建两个用户
        User user1 = new User("001", "翠翠不beautiful", 1000.0);
        User user2 = new User("002", "石栗~大彭", 500.0);

        //查看初始余额
        user1.printBalance();
        user2.printBalance();
        System.out.println();

        //创建红包
        RedPacket packet = new RedPacket(user1, user2, 200.0, "于正淳最帅!!!");

        //查看红包状态
        packet.printStatus();
        System.out.println();

        //发送红包
        if (packet.sendPacket()) {
            //发送成功后查看余额
            user1.printBalance();
        }
        System.out.println();

        //接收红包
        if (packet.receivePacket()) {
            //接收成功后查看余额
            user2.printBalance();
        }
        System.out.println();

        //再次查看红包状态
        packet.printStatus();
        System.out.println();

        //尝试重复接收
        packet.receivePacket();
    }
}