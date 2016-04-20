package org.aaf.auth;


import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 *
 * @author abimael
 */
public class Realm extends AuthorizingRealm {

//    protected Map<Usuario, AuthorizationInfo> permissoes = new HashMap<Usuario, AuthorizationInfo>();

    public Realm() {
        setName("AutorizaçãoWeb");
    }

    
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
    	System.out.println("---------------------------------");

//        Usuario usuario = (Usuario) SecurityUtils.getSubject().getPrincipal();
//
//        if (usuario != null) {
//
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//           
//                if (permissoes.containsKey(usuario)) {
//                    return permissoes.get(usuario);
//                }


//                UsuarioFacade uf = new UsuarioFacade();
//                usuario = uf.find(usuario.getId());
//
//                for (Grupo grupo : usuario.getGrupos()) {
//                    info.addRole(grupo.getNome());
//
//                    PermissaoFacade pf = (PermissaoFacade) JpaPool.get(Permissao.class);
//
//                    HashMap<String, Object> filtros = new HashMap<String, Object>();
//
//                    filtros.put("grupos.id", grupo.getId());
//
//                    List<String> listaPermissoes = new ArrayList<String>();
//
//                    for (Permissao p : pf.findRange(filtros)) {
//                        listaPermissoes.add(p.getPermissao());
//
//                    }
//
//                    info.addStringPermissions(listaPermissoes);
//                }
//
//                permissoes.put(usuario, info);
//            
//            return info;
//        } else {

            return null;
//        }
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.onLogout(principals);
        try {
            for (Object p : principals.asList()) {
          //      permissoes.remove((Usuario) p);
            }
        } catch (Exception ex) {
            Logger.getLogger(Realm.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected SaltedAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
    	System.out.println("---*******************-");
        HashMap<String, Object> filtros = new HashMap<String, Object>();
        HashMap<String, Object> filtroSalt = new HashMap<String, Object>();

        String senha = "";

        for (char c : (char[]) at.getCredentials()) {
            senha += c;
        }

        filtros.put("login", at.getPrincipal());
        
        filtroSalt.put("login", at.getPrincipal());

        Map<String, ArrayList<String>> operadores = new HashMap<String, ArrayList<String>>();

        ArrayList<String> campos = new ArrayList<String>();

        campos.add("login");
        campos.add("senha");

//        operadores.put(CriteriaBuilderUtils.EQUAL, campos);
//
//        UsuarioFacade uf = new UsuarioFacade();
//
//        Usuario usuarioSalt = uf.find(filtroSalt);
//        
//        filtros.put("senha", Criptografia.md5(senha + usuarioSalt.getSalt()));
//        Usuario usuario = uf.find(filtros, operadores);
//
//        if (usuarioSalt !=null && usuario != null) {
//
//            try {
//                LoginFacade lf = (LoginFacade) JpaPool.getJpaPool().getFacade(Login.class);
//
//                Login login = new Login();
//                login.setDataLogin(new Date());
//
//                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//                Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
//
//                login.setIp(request.getRemoteAddr());
//                login.setUsuario(usuario);
//
//                String headers = "";
//                Enumeration<String> cabecalho = request.getHeaderNames();
//                
//                for(String k : requestMap.keySet()) {
//                    
//                    headers += k + "=" + requestMap.get(k) + "\n";
//                }
//
//                login.setHeaders(headers);
//
//                lf.create(login);
//
//            } catch (Exception ex) {
//                Logger.getLogger(Realm.class.getSimpleName()).log(Level.WARNING, null, ex);
//            }
//
//            Md5Hash hashedPassword = new Md5Hash(senha, usuario.getSalt());
//            return new SimpleAuthenticationInfo(usuario, hashedPassword, new Salt(usuario.getSalt()), usuario.getLogin());
//            
//        } else {
//            return null;
//        }
        
        return null;
    }
    
//    private class Salt implements ByteSource {
//        private final String salt;
//
//        private Salt(String salt) {
//            this.salt = salt;
//        }
//
//        @Override
//        public byte[] getBytes() {
//            return salt.getBytes();
//        }
//
//        @Override
//        public String toHex() {
//            throw new UnsupportedOperationException("Not supported yet.");
//        }
//
//        @Override
//        public String toBase64() {
//            throw new UnsupportedOperationException("Not supported yet.");
//        }
//    }
}