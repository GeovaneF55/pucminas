import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class Model {
    public static void run() {
        glp_prob lp;
        glp_smcp parm;
        SWIGTYPE_p_int ind;
        SWIGTYPE_p_double val;
        int ret;

        try {
            // Create problem
            lp = GLPK.glp_create_prob();
            System.out.println("Problem created");
            GLPK.glp_set_prob_name(lp, "myProblem");

            // ** Função Objetiva **
            // Definir colunas (variáveis)
            GLPK.glp_add_cols(lp, 15);
            GLPK.glp_set_col_name(lp, 1, "xa1t1");
            GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_LO, 0.0, 0.0);            
            GLPK.glp_set_col_name(lp, 2, "xa1t2");
            GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_LO, 0.0, 0.0);         
            GLPK.glp_set_col_name(lp, 3, "xa1t3");
            GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_LO, 0.0, 0.0);            
            GLPK.glp_set_col_name(lp, 4, "xa1t4");
            GLPK.glp_set_col_kind(lp, 4, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 4, GLPKConstants.GLP_LO, 0.0, 0.0);           
            GLPK.glp_set_col_name(lp, 5, "xa1t5");
            GLPK.glp_set_col_kind(lp, 5, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 5, GLPKConstants.GLP_LO, 0.0, 0.0);

            GLPK.glp_set_col_name(lp, 6, "xa2t1");
            GLPK.glp_set_col_kind(lp, 6, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 6, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 7, "xa2t2");
            GLPK.glp_set_col_kind(lp, 7, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 7, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 8, "xa2t3");
            GLPK.glp_set_col_kind(lp, 8, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 8, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 9, "xa2t4");
            GLPK.glp_set_col_kind(lp, 9, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 9, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 10, "xa2t5");
            GLPK.glp_set_col_kind(lp, 10, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 10, GLPKConstants.GLP_LO, 0.0, 0.0);

            GLPK.glp_set_col_name(lp, 11, "xa3t1");
            GLPK.glp_set_col_kind(lp, 11, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 11, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 12, "xa3t2");
            GLPK.glp_set_col_kind(lp, 12, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 12, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 13, "xa3t3");
            GLPK.glp_set_col_kind(lp, 13, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 13, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 14, "xa3t4");
            GLPK.glp_set_col_kind(lp, 14, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 14, GLPKConstants.GLP_LO, 0.0, 0.0);
            GLPK.glp_set_col_name(lp, 15, "xa3t5");
            GLPK.glp_set_col_kind(lp, 15, GLPKConstants.GLP_CV);
            GLPK.glp_set_col_bnds(lp, 15, GLPKConstants.GLP_LO, 0.0, 0.0);

            //Criar constantes
            //Alocação de memória
            ind = GLPK.new_intArray(15);
            val = GLPK.new_doubleArray(15);


            // ** Restrições **    
            //Criar linhas (variáveis artificiais)
            GLPK.glp_add_rows(lp, 16);

            // Restrições Alunos
            GLPK.glp_set_row_name(lp, 1, "a1");
            GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_DB, 1.0, 4.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.intArray_setitem(ind, 4, 4);
            GLPK.intArray_setitem(ind, 5, 5);
            GLPK.doubleArray_setitem(val, 1, 1.);
            GLPK.doubleArray_setitem(val, 2, 1.);
            GLPK.doubleArray_setitem(val, 3, 1.);
            GLPK.doubleArray_setitem(val, 4, 1.);
            GLPK.doubleArray_setitem(val, 5, 1.);
            GLPK.glp_set_mat_row(lp, 1, 5, ind, val);

            GLPK.glp_set_row_name(lp, 2, "a2");
            GLPK.glp_set_row_bnds(lp, 2, GLPKConstants.GLP_DB, 1.0, 4.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.intArray_setitem(ind, 4, 4);
            GLPK.intArray_setitem(ind, 5, 5);
            GLPK.doubleArray_setitem(val, 1, 1.);
            GLPK.doubleArray_setitem(val, 2, 1.);
            GLPK.doubleArray_setitem(val, 3, 1.);
            GLPK.doubleArray_setitem(val, 4, 1.);
            GLPK.doubleArray_setitem(val, 5, 1.);
            GLPK.glp_set_mat_row(lp, 2, 5, ind, val);

            GLPK.glp_set_row_name(lp, 3, "a3");
            GLPK.glp_set_row_bnds(lp, 3, GLPKConstants.GLP_DB, 1.0, 4.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.intArray_setitem(ind, 4, 4);
            GLPK.intArray_setitem(ind, 5, 5);
            GLPK.doubleArray_setitem(val, 1, 1.);
            GLPK.doubleArray_setitem(val, 2, 1.);
            GLPK.doubleArray_setitem(val, 3, 1.);
            GLPK.doubleArray_setitem(val, 4, 1.);
            GLPK.doubleArray_setitem(val, 5, 1.);
            GLPK.glp_set_mat_row(lp, 3, 5, ind, val);
    

            // Restrições Tarefas
            GLPK.glp_set_row_name(lp, 4, "t1");
            GLPK.glp_set_row_bnds(lp, 4, GLPKConstants.GLP_DB, 1.0, 13.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.doubleArray_setitem(val, 1, 3.);
            GLPK.doubleArray_setitem(val, 2, 8.);
            GLPK.doubleArray_setitem(val, 3, 8.);
            GLPK.glp_set_mat_row(lp, 4, 3, ind, val);

            GLPK.glp_set_row_name(lp, 5, "t2");
            GLPK.glp_set_row_bnds(lp, 5, GLPKConstants.GLP_DB, 1.0, 8.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.doubleArray_setitem(val, 1, 5.);
            GLPK.doubleArray_setitem(val, 2, 5.);
            GLPK.doubleArray_setitem(val, 3, 3.);
            GLPK.glp_set_mat_row(lp, 5, 3, ind, val);

            GLPK.glp_set_row_name(lp, 6, "t3");
            GLPK.glp_set_row_bnds(lp, 6, GLPKConstants.GLP_DB, 1.0, 8.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.doubleArray_setitem(val, 1, 5.);
            GLPK.doubleArray_setitem(val, 2, 5.);
            GLPK.doubleArray_setitem(val, 3, 3.);
            GLPK.glp_set_mat_row(lp, 6, 3, ind, val);

            GLPK.glp_set_row_name(lp, 7, "t4");
            GLPK.glp_set_row_bnds(lp, 7, GLPKConstants.GLP_DB, 1.0, 8.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.doubleArray_setitem(val, 1, 3.);
            GLPK.doubleArray_setitem(val, 2, 2.);
            GLPK.doubleArray_setitem(val, 3, 2.);
            GLPK.glp_set_mat_row(lp, 7, 3, ind, val);

            GLPK.glp_set_row_name(lp, 8, "t5");
            GLPK.glp_set_row_bnds(lp, 8, GLPKConstants.GLP_DB, 1.0, 5.0);
            GLPK.intArray_setitem(ind, 1, 1);
            GLPK.intArray_setitem(ind, 2, 2);
            GLPK.intArray_setitem(ind, 3, 3);
            GLPK.doubleArray_setitem(val, 1, 8.);
            GLPK.doubleArray_setitem(val, 2, 3.);
            GLPK.doubleArray_setitem(val, 3, 2.);
            GLPK.glp_set_mat_row(lp, 8, 3, ind, val);

            // Desalocar memória
            GLPK.delete_intArray(ind);
            GLPK.delete_doubleArray(val);

            // Coeficientes da função objetiva
            GLPK.glp_set_obj_name(lp, "z");
            GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);
            GLPK.glp_set_obj_coef(lp, 1, 3.0);
            GLPK.glp_set_obj_coef(lp, 2, 5.0);
            GLPK.glp_set_obj_coef(lp, 3, 5.0);
            GLPK.glp_set_obj_coef(lp, 4, 3.0);
            GLPK.glp_set_obj_coef(lp, 5, 8.0);
            
            GLPK.glp_set_obj_coef(lp, 6, 8.0);
            GLPK.glp_set_obj_coef(lp, 7, 5.0);
            GLPK.glp_set_obj_coef(lp, 8, 5.0);
            GLPK.glp_set_obj_coef(lp, 9, 2.0);
            GLPK.glp_set_obj_coef(lp, 10, 3.0);
            
            GLPK.glp_set_obj_coef(lp, 11, 8.0);
            GLPK.glp_set_obj_coef(lp, 12, 3.0);
            GLPK.glp_set_obj_coef(lp, 13, 3.0);
            GLPK.glp_set_obj_coef(lp, 14, 2.0);
            GLPK.glp_set_obj_coef(lp, 15, 2.0);

            // Escrever o modelo em um arquivo
            // GLPK.glp_write_lp(lp, null, "model.lp");

            // Resolver o modelo
            parm = new glp_smcp();
            GLPK.glp_init_smcp(parm);
            ret = GLPK.glp_simplex(lp, parm);

            // Restaurar solução
            if (ret == 0) {
                write_lp_solution(lp);
            } else {
                System.out.println("O problema não pôde ser resolvido.");
            }

            // Desalocar memória
            GLPK.glp_delete_prob(lp);
        } catch (GlpkException ex) {
            ex.printStackTrace();
	    ret = 1;
        }

        System.exit(ret);
    }  

    /**
     * Escreve a resolução utilizando o método Simplex
     * @param lp problema
     */
    static void write_lp_solution(glp_prob lp) {
        int i;
        int n;
        String name;
        double val;

        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        System.out.print(name);
        System.out.print(" = ");
        System.out.println(val);
        n = GLPK.glp_get_num_cols(lp);
        for (i = 1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            val = GLPK.glp_get_col_prim(lp, i);
            System.out.print(name);
            System.out.print(" = ");
            System.out.println(val);
        }
    }
}
