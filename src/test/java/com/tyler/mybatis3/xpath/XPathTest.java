package com.tyler.mybatis3.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XPathTest {

  public static void main(String[] args) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    //开启验证
    documentBuilderFactory.setValidating(true);
    documentBuilderFactory.setNamespaceAware(false);
    documentBuilderFactory.setIgnoringComments(true);
    documentBuilderFactory.setIgnoringElementContentWhitespace(false);
    documentBuilderFactory.setCoalescing(false);
    documentBuilderFactory.setExpandEntityReferences(true);

    //创建documentBuilder
    DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

    //设置异常处理对象
    builder.setErrorHandler(new ErrorHandler() {
      @Override
      public void warning(SAXParseException exception) throws SAXException {
        System.out.println("error:" + exception.getMessage());
      }

      @Override
      public void error(SAXParseException exception) throws SAXException {
        System.out.println("fatalError:" + exception.getMessage());
      }

      @Override
      public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("WARN:" + exception.getMessage());
      }
    });

    //将文档加载到一个Document对象中
    Document doc = builder.parse("/Users/tyler/code/github/mybatis-3/mybatis-debug/src/main/java/com/tyler/mybatis3/xpath/inventory.xml");
    //创建xpathfactory
    XPathFactory factory = XPathFactory.newInstance();
    //创建XPath对象
    XPath xpath = factory.newXPath();
    //编译XPath表达式
    XPathExpression expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");

    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    System.out.println("查询作者为Neal Stephenson的图书的标题:");
    NodeList nodes = (NodeList) result;
    for (int i = 0; i < nodes.getLength(); i++) {
      System.out.println(nodes.item(i).getNodeValue());
    }
    System.out.println("查询1997年之后的图书的标题:");
    nodes = (NodeList) xpath.evaluate("//book[@year>1997]/title/text()", doc, XPathConstants.NODESET);
    for (int i = 0; i < nodes.getLength(); i++) {
      System.out.println(nodes.item(i).getNodeValue());
    }
  }
}
