package entities;

import game.Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import components.IScorable;

public class ScoreManager extends GameObject implements IScorable{
	
	private int scorePlayer;
	private int scoreEnemy;
	private Font font;
	
	public ScoreManager() {
		scoreEnemy = 0;
		scorePlayer = 0;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (Pong.getGameWidth() - metrics.stringWidth("0    0"))/2;
		int y =  metrics.getAscent() + 10;
		
		g.drawString(scoreEnemy+"    "+scorePlayer, x, y);		
	}

	@Override
	public void update(double delta) {
		
	}

	public int getScorePlayer() {
		return scorePlayer;
	}

	public void setScorePlayer(int scorePlayer) {
		this.scorePlayer = scorePlayer;
	}

	public int getScoreEnemy() {
		return scoreEnemy;
	}

	public void setScoreEnemy(int scoreEnemy) {
		this.scoreEnemy = scoreEnemy;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public void addPointPlayer() {
		scorePlayer++;
	}

	@Override
	public void addPointEnemy() {
		scoreEnemy++;		
	}

}
