/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.pdtextensions.core.ui.codemanipulation;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IType;
import org.eclipse.php.ui.CodeGeneration;
import org.pdtextensions.core.log.Logger;

public class InterfaceStub extends ElementStub {

	public InterfaceStub(IScriptProject scriptProject, String name, String namespace, List<IType> ancestors, boolean generateComments) {
		this.scriptProject = scriptProject;
		interfaces = ancestors;
		this.generateComments = generateComments;
		this.name = name;
		this.namespace = namespace;
	}

	@Override
	protected void generateCode() {

		try {
			StringBuilder buffer = new StringBuilder("<?php");
			buffer.append(lineDelim);

			buffer.append(generateNamespacePart());

			if (generateComments == true)
				buffer.append(CodeGeneration.getTypeComment(scriptProject, name, lineDelim) + lineDelim);

			if (isFinal == true) {
				buffer.append("final ");
			}

			buffer.append("interface " + name);

			buffer.append(generateAncestorsPart());

			buffer.append("{" + lineDelim);

			buffer.append(lineDelim + "}");
			code = buffer.toString();
		} catch (CoreException e) {
			Logger.logException(e);
		}
	}

	public String generateAncestorsPart() {

		return "";
	}

}