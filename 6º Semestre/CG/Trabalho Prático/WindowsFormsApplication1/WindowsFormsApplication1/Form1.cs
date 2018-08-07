using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class tela : Form
    {
        Bitmap areaDesenho;
        Color corPreenche;
        
        public tela()
        {
            InitializeComponent();            
            areaDesenho = new Bitmap(imagem.Size.Width, imagem.Size.Height);
            corPreenche = Color.Black;                       
        }

       /* private void mudaFocoImagem()
        {
            if (imagem.CanFocus)
            {
                imagem.Focus();
            }
        }*/

        private void desenhar_Click(object sender, EventArgs e)
        {
            int x = (int) Convert.ToInt64(txtX.Text);
            int y = (int) Convert.ToInt64(txtY.Text);

            areaDesenho.SetPixel(x, y, corPreenche);
            imagem.Image = areaDesenho;
        }

        private void btCor_Click(object sender, EventArgs e)
        {
            DialogResult result = cdlg.ShowDialog();
            if (result == DialogResult.OK)
            {                
                corPreenche = cdlg.Color;
            }
        }

        private void btApagar_Click(object sender, EventArgs e)
        {
            areaDesenho = new Bitmap(imagem.Size.Width, imagem.Size.Height);
            imagem.Image = areaDesenho;
        }

        private void imagem_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                int x = e.X;
                int y = e.Y;

                txtX.Text = Convert.ToString(x);
                txtY.Text = Convert.ToString(y);

                areaDesenho.SetPixel(x, y, corPreenche);
                imagem.Image = areaDesenho;
            }
        }
    }
}
