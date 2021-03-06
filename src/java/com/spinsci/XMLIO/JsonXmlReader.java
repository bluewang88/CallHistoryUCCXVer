/*
 * Copyright 2011 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spinsci.XMLIO;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/**
 * Helper class that can be used for JSON -> XML transformation.
 * <pre>
 *	Transformer transformer = TransformerFactory.newInstance().newTransformer();
 *	InputSource source = new InputSource(...);
 *	Result result = ...;
 *	transformer.transform(new SAXSource(new JsonXmlReader(namespace),source), result);
 * </pre>
 */
public class JsonXmlReader implements XMLReader {

    private ContentHandler contentHandler;
    private final String namespaceUri;
    private final boolean addTypeAttributes;


    public JsonXmlReader() {
        this("");
    }

    public JsonXmlReader(String namespaceUri) {
    	this(namespaceUri, false);
    }

    public JsonXmlReader(String namespaceUri, boolean addTypeAttributes) {
    	this.namespaceUri = namespaceUri;
		this.addTypeAttributes = addTypeAttributes;
	}

	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new UnsupportedOperationException();
    }

    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {

    }

    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new UnsupportedOperationException();
    }


    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        //ignore
    }

    public void setEntityResolver(EntityResolver resolver) {
    	 //ignore
    }

    public EntityResolver getEntityResolver() {
        throw new UnsupportedOperationException();
    }

    public void setDTDHandler(DTDHandler handler) {
        //ignore
    }

    public DTDHandler getDTDHandler() {
        throw new UnsupportedOperationException();
    }

    public void setContentHandler(ContentHandler handler) {
        this.contentHandler = handler;
    }

    public ContentHandler getContentHandler() {
        return contentHandler;
    }

    public void setErrorHandler(ErrorHandler handler) {
    	 //ignore

    }

    public ErrorHandler getErrorHandler() {
        throw new UnsupportedOperationException();
    }


    public void parse(InputSource input) throws IOException, SAXException {
        JsonParser jsonParser = new JsonFactory().createJsonParser(input.getCharacterStream());
        new JsonSaxAdapter(jsonParser, contentHandler, namespaceUri, addTypeAttributes).parse();

    }

    public void parse(String systemId) throws IOException, SAXException {
        throw new UnsupportedOperationException();
    }

    public  String getNamespaceUri() {
        return namespaceUri;
    }


}
