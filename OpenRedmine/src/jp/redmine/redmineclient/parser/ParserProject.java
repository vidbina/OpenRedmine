package jp.redmine.redmineclient.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import jp.redmine.redmineclient.entity.RedmineConnection;
import jp.redmine.redmineclient.entity.RedmineProject;
import jp.redmine.redmineclient.entity.TypeConverter;

public class ParserProject extends BaseParserInternal<RedmineConnection,RedmineProject> {

	@Override
	protected String getProveTagName() {
		return "project";
	}

	@Override
	protected RedmineProject getNewProveTagItem() {
		return new RedmineProject();
	}

	@Override
	protected void parseInternal(RedmineConnection con, RedmineProject item)
			throws XmlPullParserException, IOException{
		if(xml.getDepth() <= 2)
			return;
		if("id".equalsIgnoreCase(xml.getName())){
			String work = getNextText();
			if("".equals(work))	return;
			item.setProjectId(Integer.parseInt(work));
		} else if("name".equalsIgnoreCase(xml.getName())){
			item.setName(getNextText());
		} else if("identifier".equalsIgnoreCase(xml.getName())){
			item.setIdentifier(getNextText());
		} else if("description".equalsIgnoreCase(xml.getName())){
			item.setDescription(getNextText());
		} else if("homepage".equalsIgnoreCase(xml.getName())){
			item.setHomepage(getNextText());
		} else if("parent".equalsIgnoreCase(xml.getName())){
			String work = getNextText();
			if("".equals(work))	return;
			item.setParent(Integer.parseInt(work));
		} else if("created_on".equalsIgnoreCase(xml.getName())){
			item.setCreated(TypeConverter.parseDateTime(getNextText()));
		} else if("updated_on".equalsIgnoreCase(xml.getName())){
			item.setModified(TypeConverter.parseDateTime(getNextText()));
		}
		//TODO tracker, issue_categories

	}
}
