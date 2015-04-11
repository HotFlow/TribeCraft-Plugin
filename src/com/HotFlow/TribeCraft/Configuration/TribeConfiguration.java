package com.HotFlow.TribeCraft.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

/**
 * @author HotFlow
 */
public class TribeConfiguration extends YamlConfiguration {
	private final DumperOptions yamlOptions = new DumperOptions();
	private final Representer yamlRepresenter = new YamlRepresenter();
	private final Yaml yaml = new Yaml(new YamlConstructor(),
			this.yamlRepresenter, this.yamlOptions);

	public TribeConfiguration() {
		super();
	}

	/**
	 * ����config
	 * 
	 * @param file
	 *            �ļ�
	 * @throws IOException
	 */
	@Override
	public void save(File file) throws IOException {
		this.createParentDirs(file);

		String data = saveToString();

		FileWriter writer = new FileWriter(file);
		try {
			writer.write(data);
		} finally {
			writer.close();
		}
	}

	/**
	 * ����config
	 * 
	 * @param file
	 *            �ļ�·��
	 * @throws IOException
	 */
	@Override
	public void save(String file) throws IOException {
		save(new File(file));
	}

	/**
	 * ����
	 * 
	 * @param file
	 * @throws java.io.FileNotFoundException
	 * @throws org.bukkit.configuration.InvalidConfigurationException
	 */
	@Override
	public void load(File file) throws FileNotFoundException, IOException,
			InvalidConfigurationException {
		load(new FileInputStream(file));
	}

	/**
	 * ����
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	@Override
	public void load(InputStream stream) throws IOException,
			InvalidConfigurationException {
		InputStreamReader reader = new InputStreamReader(stream);
		StringBuilder builder = new StringBuilder();
		BufferedReader input = new BufferedReader(reader);
		ArrayList<String> builders = new ArrayList<String>();
		try {
			String line;
			while ((line = input.readLine()) != null) {
				builders.add(line);
				builder.append(line);
				builder.append('\n');
			}
		} finally {
			input.close();
		}

		super.loadFromString(builder.toString());
	}

	/**
	 * ���� config
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	@Override
	public void load(String file) throws FileNotFoundException, IOException,
			InvalidConfigurationException {
		load(new File(file));
	}

	/**
	 * �޸�1.7.10����Ϊ����
	 * 
	 * @param contents
	 * @throws org.bukkit.configuration.InvalidConfigurationException
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void loadFromString(String contents)
			throws InvalidConfigurationException {
		Map input;
		try {
			input = (Map) this.yaml.load(contents);
		} catch (YAMLException e) {
			throw new InvalidConfigurationException(e);
		} catch (ClassCastException e) {
			throw new InvalidConfigurationException("Top level is not a Map.");
		}

		String header = parseHeader(contents);
		if (header.length() > 0) {
			options().header(header);
		}

		if (input != null) {
			convertMapsToSections(input, this);
		}
	}

	/**
	 * �޸�1.7.10����Ϊ����
	 * 
	 * @return
	 */
	@Override
	public String saveToString() {
		this.yamlOptions.setIndent(options().indent());
		this.yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		this.yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

		String header = buildHeader();
		String dump = this.yaml.dump(getValues(false));

		if (dump.equals("{}\n")) {
			dump = "";
		}

		return header + dump;
	}

	/**
	 * �������ļ���
	 * 
	 * @param file
	 * @throws IOException
	 */
	private void createParentDirs(File file) throws IOException {
		File parent = file.getCanonicalFile().getParentFile();
		if (parent == null) {
			return;
		}

		parent.mkdirs();

		if (!parent.isDirectory()) {
			throw new IOException("Unable to create parent directories of "
					+ file);
		}
	}

}
