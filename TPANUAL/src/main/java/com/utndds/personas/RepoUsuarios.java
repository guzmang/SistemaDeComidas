package com.utndds.personas;

import java.util.List;

public interface RepoUsuarios {

	public void add(Usuario usuario);

	public void remove(Usuario usuario);

	public void update(Usuario usuario);

	public Usuario get(Usuario usuario);

	public List<Usuario> list(Usuario usuario);

}
