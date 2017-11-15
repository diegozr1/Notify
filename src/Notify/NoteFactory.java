package Notify;

public class NoteFactory implements Note{
	
	public String title = "";
	public String text = "";
	public long time= 60;
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		this.text = text;
	}

	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return this.time;
	}

	@Override
	public void setTime(long time) {
		// TODO Auto-generated method stub
		
	}

}
