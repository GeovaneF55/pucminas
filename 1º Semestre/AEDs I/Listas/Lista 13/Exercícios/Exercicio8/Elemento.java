public class Elemento{
   private String descricao;
   private int qualidade;
   
   Elemento(String descricao, int qualidade){
      setDescricao(descricao);
      setQualidade(qualidade);
   }
   
   public void setDescricao(String descricao){
      if(descricao.length() <= 20){
         this.descricao = descricao;
      }else{
         this.descricao = "SEM DESCRIÇÃO";
      }
   }
   
   public void setQualidade(int qualidade){
      if(qualidade >=0 && qualidade <=10){
         this.qualidade = qualidade;
      }else{
         this.qualidade = 0;
      }
   }
   
   public String getDescricao(){
      return this.descricao;
   }
   
   public int getQualidade(){
      return this.qualidade;
   }
}