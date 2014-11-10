package com.avalon.client.social;

/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.avalon.client.UIThread;
import com.avalon.client.GameMain;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserFunction;

/**
 * @author Christopher Deckers
 */
public class GetAccessToken extends JPanel {
	private static JFrame upperFrame;
	private UIThread thread;
	private static String ACCESS_TOKEN = "";
	JPanel webBrowserPanel = new JPanel(new BorderLayout());
  public GetAccessToken(final JFrame frame,final UIThread tClient) {
	  
    super(new BorderLayout());
    upperFrame = frame;
    thread = tClient;
    
    //webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    final JWebBrowser webBrowser = new JWebBrowser();
    webBrowser.navigate("http://avalon.changikhwan.com:9090/fb/step1.jsp");
    webBrowser.setStatusBarVisible(false);
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    
    add(webBrowserPanel, BorderLayout.CENTER);
    // Create an additional bar allowing to show/hide the menu bar of the web browser.
     
    webBrowser.setMenuBarVisible(false);
   
       
      webBrowser.registerFunction(new WebBrowserFunction("testFunc") {   
        @Override  
        public Object invoke(JWebBrowser webBrowser, Object... args) {   
          //System.out.println(args[0].toString()); // FACEBOOK TOKEN
          ACCESS_TOKEN = args[0].toString();
          //webBrowserPanel.setVisible(false);
          System.out.println("Sdfsdfsdf");
          
          upperFrame.setVisible(false);
          upperFrame.dispose();
          UIThread.setACCESS_TOKEN(ACCESS_TOKEN);
          
          return null;
        }   
      });  
    
  }

}