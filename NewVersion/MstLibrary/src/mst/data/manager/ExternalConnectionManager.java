/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import mst.data.connection.DriverTypes;
import mst.data.connection.DbConnection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 */
public class ExternalConnectionManager {

    private String _externalConfFilePath = "";
    private boolean _autoConfFileCreation = false;
    private HashMap map;

    public ExternalConnectionManager(String confFilePath) throws Exception {
        this(confFilePath, false);
    }

    public ExternalConnectionManager(String confFilePath,
            boolean autoFileCreation) throws Exception {
        _externalConfFilePath = confFilePath;
        _autoConfFileCreation = autoFileCreation;
        map = getMap(confFilePath);
    }

    public final Object getValueOfKey(Object key) throws Exception {
        return map.get(key);
    }

    public DbConnection createConnection() throws Exception {
        Object obj;

        obj = getValueOfKey("ext-driver");
        String Driver = obj != null ? obj.toString() : "";

        obj = getValueOfKey("ext-url");
        String Url = obj != null ? obj.toString() : "";

        obj = getValueOfKey("ext-user");
        String user = obj != null ? obj.toString() : "";

        obj = getValueOfKey("ext-password");
        String pass = obj != null ? obj.toString() : "";

        DbConnection dbConn = null;
        if (user.length() > 0) {
            dbConn = DbConnection.createDbConn(DriverTypes.External, Url, user, pass);
        } else {
            dbConn = DbConnection.createDbConn(DriverTypes.External, Url);
        }
        dbConn.setExternalDriver(Driver);
        return dbConn;
        //DbConnection dbConn=new DbConnection(ConnectionTypes.External, Url, user, user)
    }

    /**
     * @return the externalConfFilePath
     */
    public String getExternalConfFilePath() {
        return _externalConfFilePath;
    }

    /**
     * @param externalConfFilePath the externalConfFilePath to set
     */
    public void setExternalConfFilePath(String externalConfFilePath) {
        this._externalConfFilePath = externalConfFilePath;
    }

    /**
     * @return the autoConfFileCreation
     */
    public boolean isAutoConfFileCreation() {
        return _autoConfFileCreation;
    }

    /**
     * @param autoConfFileCreation the autoConfFileCreation to set
     */
    public void setAutoConfFileCreation(boolean autoConfFileCreation) {
        this._autoConfFileCreation = autoConfFileCreation;
    }

    private void reCreateFile() throws Exception {
        try {
            if (!_autoConfFileCreation) {
                return;
            }

            if (_externalConfFilePath == null || _externalConfFilePath.length() == 0) {
                throw new IllegalArgumentException("external conf file path msut be valid.");
            }
            DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();
            Document xmlDoc = docBuilder.newDocument();

            Element root = xmlDoc.createElement("ext-connection");
            xmlDoc.appendChild(root);

            Element driver = xmlDoc.createElement("ext-driver");
            driver.setTextContent("driver_external");
            root.appendChild(driver);

            Element url = xmlDoc.createElement("ext-url");
            url.setTextContent("url_external");
            root.appendChild(url);

            Element user = xmlDoc.createElement("ext-user");
            user.setTextContent("user_external");
            root.appendChild(user);

            Element pass = xmlDoc.createElement("ext-pass");
            pass.setTextContent("password_external");
            root.appendChild(pass);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer transformer = transFact.newTransformer();
            DOMSource domSrc = new DOMSource(xmlDoc);
            File file = new File(_externalConfFilePath);
            if (file.exists()) {
                if (!file.canWrite()) {
                    throw new IllegalAccessException(
                            String.format("%s file can not be written, it must be writable.",
                            _externalConfFilePath));
                } else {
                    file.renameTo(new File(_externalConfFilePath.concat(
                            new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()))));
                }
            }

            StreamResult streamRslt = new StreamResult(new File(_externalConfFilePath));
            transformer.transform(domSrc, streamRslt);
            return;
        } catch (Exception exc) {
            throw exc;
        }
    }

    protected HashMap getMap(String confFile) throws Exception {
        try {
            HashMap hm = new HashMap();
            File f = new File(confFile);
            DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();
            Document doc = docBuilder.parse(f);
            doc.getDocumentElement().normalize();/*
             System.out.printf("Main Node : %s\nMain Node Value: %s\n", doc
             .getDocumentElement().getNodeName(), doc
             .getDocumentElement().getTextContent());*/
            NodeList nodeList = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    hm.put(elem.getNodeName(), elem.getTextContent());
                }
            }
            return hm;
        } catch (Exception exc) {
            reCreateFile();
            return getMap(confFile);
            //throw exc;
        }
    }
}
